#Handler
##Handler的实现原理

handler就消息处理的五大组成部分：Message，Handler，Message Queue，Looper和ThreadLocal

##1、Handler Looper message三者之间的关系是什么？

- Message:  需要传递的消息，可以传递数据；
 它的作用仅限于线程之间通信的时候传递消息，他可以携带少量数据，用于线程之间传递信息，常用的四个字段target，what，obj，arg;
 - target:消息回调后的作用域类，通常是一个handler。
 - what:是一个区分不同消息的标识符。
 - obj：这是obj是一个对象类型，可以携带自定义的类。
 - arg：int类型，携带的参数

- Handler: 消息辅助类，主要功能向消息池发送各种消息事件（Handler.sendMessage）和处理相应消息事件(Handler.handleMessage)
- MessageQueue: 消息队列，但是它的内部实现并不是用的队列，实际上是通过一个单链表的数据结构来维护消息列表，因为单链表在插入和删除上比较有优势。主要功能向消息池投递消息（MessageQueue.enqueueMessage）和取走消息池的消息（MessageQueue.next）
每一个线程只有一直MessageQueue队列
- Looper:不断循环执行（Looper.loop），从MessageQueue中读取消息，按分发机制将消息分发给目标处理者
 每个线程中只会有一个Looper对象

MessageQueue，Handler和Looper三者之间的关系：
每个线程中只能存在一个Looper,Looper是保存在ThreadLocal中的。主线程（UI线程）已经创建了一个Looper,所以主线程不需要在创建Looper，但是在其他线程中需要创建Looper。每个线程中可以有多个Handler，即一个Looper可以处理来自多个Handler的消息。Looper中维护一个MessageQueue，来维护消息队列，消息队列中的Message可以来自不同的Handler.

## 2.子线程中能不能直接new一个Handler,为什么主线程可以，主线程的Looper第一次调用loop方法,什么时候,哪个类
子线程不能直接new一个handler，要在子线程中调用Looper.prepare();以及 getLooper();方法并且将get到的looper传递到handler的构造函数中，并且调用
 Looper.loop();开启循环
主线程可以直接new一个handler是因为主线程会自动创建Looper对象并且开启消息循环

主线程的Looper第一次调用loop方法是在ActivityThread.java中的main函数

##3.Handler导致的内存泄露原因及其解决方案

### 须知：

主线程Looper生命周期和Activity的生命周期一致。
非静态内部类，或者匿名内部类。默认持有外部类引用。
### 原因：

Handler造成内存泄露的原因。非静态内部类，或者匿名内部类。使得Handler默认持有外部类的引用。在Activity销毁时，由于Handler可能有未执行完/正在执行的Message。导致Handler持有Activity的引用。进而导致GC无法回收Activity。

### 可能造成内存泄漏

   匿名内部类:
   ` 
     
      
      Handler handler=new Handler(){

         @Override 

         public void handleMessage(Message msg) {

         super.handleMessage(msg);

         }

     };`

非静态内部类:

    `
    protected class AppHandler extends Handler {
    @Override

    public void handleMessage(Message msg) {
       switch (msg.what) {
        // TODO: 2019/4/30

        }

      }

    }

    `

### 解决方法：

 - Activity销毁时，清空Handler中，未执行或正在执行的Callback以及Message。

    `// 清空消息队列，移除对外部类的引用

    @Override

    protected void onDestroy() {
      super.onDestroy();

     mHandler.removeCallbacksAndMessages(null);

    }`

 -  静态内部类+弱引用
 
 
 `    
    

    private static class AppHandler extends Handler {
    //弱引用，在垃圾回收时，被回收

      WeakReference activity;

      AppHandler(Activity activity){
       this.activity=new WeakReference(activity);

      }

      public void handleMessage(Message message){
       switch (message.what){
        //todo

        }

       }

     }
`

####总结：
Handler 允许我们发送延时消息， 如果在延时期间用户关闭了 Activity， 那么该Activity 会泄露。
这个泄露是因为 Message 会持有 Handler， 而又因为 Java 的特性， 内部类会
持有外部类， 使得 Activity 会被 Handler 持有， 这样最终就导致 Activity 泄露。
解决该问题的最有效的方法是： 将 Handler 定义成静态的内部类， 在内部持有
Activity 的弱引用， 并及时移除所有消息。并且再在 Activity.onDestroy() 前移除消息mWorkHandler.removeCallbacksAndMessages(null);， 加一层保障，这样双重保障， 就能完全避免内存泄露了。
注意： 单纯的在 onDestroy 移除消息并不保险， 因为 onDestroy 并不一定执行。

7、主线程的 Looper 不允许退出，其实原因很简单， 主线程不允许退出， 退出就意味 APP 要挂。
##4.一个线程可以有几个Handler,几个Looper,几个MessageQueue对象
一个线程可以多个Handler，一个Looper,一个MessageQueue

##5.Message对象创建的方式有哪些 & 区别？Message.obtain()怎么维护消息池的

##2、 Handler 是如何与线程关联的？
Looper 提供了 Looper.prepare() 方法来创建 Looper ， 并且会借助
ThreadLocal 来实现与当前线程的绑定功能。 Looper.loop() 则会开始不断尝试从
MessageQueue 中获取 Message , 并分发给对应的 Handler
也就是说 Handler 跟线程的关联是靠 Looper 来实现的

##3、 Handler 发出去的消息是谁管理的？
  MessageQueue 管理消息
##4、 消息又是怎么回到 handleMessage() 方法的？
  通过Looper切换线程
##5、线程的切换是怎么回事？
   
 Handler 发送的消息由 MessageQueue 存储管理， 并由 Looper 负责回调消息
到 handleMessage()。
线程的转换由 Looper 完成， handleMessage() 所在线程由 Looper.loop() 调用
者所在线程决定。

##6、AsyncTask、HandlerThread、 Messenger、 IdleHandler 和 IntentService




##8、创建 Message 实例的最佳方式
由于 Handler 极为常用， 所以为了节省开销， Android 给 Message 设计了回收
机制， 所以我们在使用的时候尽量复用 Message ， 减少内存消耗。
方法有二：
1. 通过 Message 的静态方法 Message.obtain(); 获取；
2. 通过 Handler 的公有方法 handler.obtainMessage(); 
  
##9、 知识点汇总
1. Handler 的 背 后 有 Looper 、 MessageQueue 支 撑 ， Looper 负 责 消 息 分 发 ，MessageQueue 负责消息管理；
2. 在创建 Handler 之前一定需要先创建 Looper；
3. Looper 有退出的功能， 但是主线程的 Looper 不允许退出；
4. 异步线程的 Looper 需要自己调用 Looper.myLooper().quit(); 退出；
5. Runnable 被封装进了 Message， 可以说是一个特殊的 Message；
6. Handler.handleMessage() 所在的线程是 Looper.loop() 方法被调用的线程， 也可以说成 Looper 所在的线程， 并不是创建 Handler 的线程；
7. 使用内部类的方式使用 Handler 可能会导致内存泄露， 即便在 Activity.onDestroy里移除延时消息， 必须要写成静态内部类；

android.os.HandlerThread
适合用来处于不需要更新 UI 的后台任务

android.os.AyncTask
适合用来处于需要更新 UI 的后台任务

##我们还有以下几种方法可以在子线程中进行 UI 操作：
1. Handler 的 post()方法
2. View 的 post()方法
3. Activity 的 runOnUiThread()方法

