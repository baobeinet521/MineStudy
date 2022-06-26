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
Looper.prepare()函数中知道。在Looper的prepare方法中创建了Looper对象，并放入到ThreadLocal中，并通过ThreadLocal来获取looper的对象, ThreadLocal的内部维护了一个ThreadLocalMap类,ThreadLocalMap是以当前thread做为key的,因此可以得知，一个线程最多只能有一个Looper对象， 在Looper的构造方法中创建了MessageQueue对象，并赋值给mQueue字段。因为Looper对象只有一个，那么Messagequeue对象肯定只有一个。

##5.Message对象创建的方式有哪些 & 区别？Message.obtain()怎么维护消息池的


- 1.Message msg = new Message();
每次需要Message对象的时候都创建一个新的对象，每次都要去堆内存开辟对象存储空间


- 2.Message msg = Message.obtain();
obtainMessage能避免重复Message创建对象。它先判断消息池是不是为空，如果非空的话就从消息池表头的Message取走,再把表头指向 next。
如果消息池为空的话说明还没有Message被放进去，那么就new出来一个Message对象。消息池使用 Message 链表
结构实现，消息池默认最大值 50。消息在loop中被handler分发消费之后会执行回收的操作，将该消息内部数据清空并添加到消息链表的表头。


- 3.Message msg = handler.obtainMessage(); 其内部也是调用的obtain()方法 
##6.Handler 有哪些发送消息的方法


- sendMessage(Message msg)


- sendMessageDelayed(Message msg, long uptimeMillis)


- post(Runnable r)


- postDelayed(Runnable r, long uptimeMillis)


- sendMessageAtTime(Message msg,long when) 
## 7.Handler的post与sendMessage的区别和应用场景


-Handler 的post：

  
    `public final boolean post(@NonNull Runnable r) {

        return  sendMessageDelayed(getPostMessage(r), 0);

      }
     private static Message getPostMessage(Runnable r) {

       Message m = Message.obtain();

       m.callback = r;

       return m;

    }`

可以看到hadler.post(runnable)，中的runnable最后也被封装成一个message
   ` /**
         * Handle system messages here. 
           Handler分发消息
     */
    public void dispatchMessage(@NonNull Message msg) {

       if (msg.callback != null) {
          handleCallback(msg);
       } else {
          if (mCallback != null) {
              if (mCallback.handleMessage(msg)) {
                 return;
             }
         }
         handleMessage(msg);
      }
    }`


- Handler的post与sendMessage区别：


 1. hadler.post(runnable)，中的runnable被赋值给Message 的callback ，最后也被封装成一个message，而sendMessage是直接发送一个Message


 2. dispatchMessage方法中可以看到handler在分发消息时，先判断Message的callback是否为空，不为空则调用handleCallback(msg);-->message.callback.run();消息在Runnable对象的run方法中处理，如果callback为空则判断handler的Callback是否为空，不为空调用mCallback.handleMessage(msg)处理消息，并且判断mCallback.handleMessage(msg)返回值是否为true如果为true直接返回，否则继续调用handler的handleMessage（msg）处理消息


- 应用场景：

   post一般用于单个场景 比如单一的倒计时弹框功能 sendMessage的回调需要去实现handleMessage Message则做为参数 用于多判断条件的场景 
##8.handler postDealy后消息队列有什么变化，假设先 postDelay 10s, 再postDelay 1s, 怎么处理这2条消息
使用Handler的postDealy后，MessageQueue里的消息会进行重新排序
源码：

     `      
     public final boolean postDelayed(@NonNull Runnable r, long delayMillis) {

       return sendMessageDelayed(getPostMessage(r), delayMillis);

    }
`
      `     
    
     public final boolean sendMessageDelayed(@NonNull Message msg, long delayMillis) {

       if (delayMillis < 0) {

          delayMillis = 0;
       }

      return sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);

    }`
postDelayed传入的时间，会和当前的时间SystemClock.uptimeMillis()做加和,而不是单纯的只是用延时时间。延时消息会和当前消息队列里的消息头的执行时间做对比，如果比头的时间靠前，则会做为新的消息头，不然则会从消息头开始向后遍历，找到合适的位置插入延时消息。postDelay()一个10秒钟的Runnable A、消息进队，MessageQueue调用nativePollOnce()阻塞，Looper阻塞；紧接着post()一个Runnable B、消息进队，判断现在A时间还没到、正在阻塞，把B插入消息队列的头部（A的前面），然后调用nativeWake()方法唤醒线程；MessageQueue.next()方法被唤醒后，重新开始读取消息链表，第一个消息B无延时，直接返回给Looper；Looper处理完这个消息再次调用next()方法，MessageQueue继续读取消息链表，第二个消息A还没到时间，计算一下剩余时间（假如还剩9秒）继续调用nativePollOnce()阻塞； 直到阻塞时间到或者下一次有Message进队 
##9.MessageQueue是什么数据结构
内部存储结构并不是真正的队列，而是采用单链表的数据结构来存储消息列表
这点和传统的队列有点不一样，主要区别在于Android的这个队列中的消息是按照时间先后顺序来存储的，时间较早的消息，越靠近队头。 当然，我们也可以理解成，它是先进先出的，只是这里的先依据的不是谁先入队，而是消息待发送的时间 
链表结构插入删除数据比较方便
##10.Handler怎么做到的一个线程对应一个Looper，如何保证只有一个MessageQueue,ThreadLocal在Handler机制中的作用 
设计的初衷是为了解决多线程编程中的资源共享问题，synchronized采取的是“以时间换空间”的策略，本质上是对关键资源上锁，让大家排队操作。而ThreadLocal采取的是“以空间换时间”的思路， 它一个线程内部的数据存储类，通过它可以在制定的线程中存储数据，数据存储以后，只有在指定线程中可以获取到存储的数据， 对于其他线程就获取不到数据，可以保证本线程任何时间操纵的都是同一个对象。比如对于Handler，它要获取当前线程的Looper,很显然Looper的作用域就是线程，并且不同线程具有不同的Looper。 ThreadLocal本质是操作线程中ThreadLocalMap来实现本地线程变量的存储的ThreadLocalMap是采用数组的方式来存储数据，其中key(弱引用)指向当前ThreadLocal对象，value为设的值 
###ThreadLocal在Handler机制中的作用：
Handler机制中，ThreadLocal用来保存Looper对象,确保每一个线程只有一个Looper
##11. HandlerThread是什么 & 好处 &原理 & 使用场景 
HandlerThread本质上是一个线程类，它继承了Thread； HandlerThread有自己的内部Looper对象，通过Looper.loop()进行looper循环；

通过获取HandlerThread的looper对象传递给Handler对象，然后在handleMessage()方法中执行异步任务；

###优势:
1.将loop运行在子线程中处理,减轻了主线程的压力,使主线程更流畅,有自己的消息队列,不会干扰UI线程

2.串行执行,开启一个线程起到多个线程的作用

###劣势:
1.由于每一个任务队列逐步执行,一旦队列耗时过长,消息延时

2.对于IO等操作,线程等待,不能并发
我们可以使用HandlerThread处理本地IO读写操作（数据库，文件），因为本地IO操作大多数的耗时属于毫秒级别，对于单线程 + 异步队列的形式 不会产生较大的阻塞 

##12.IdleHandler及其使用场景 
Handler 机制提供的一种，可以在 Looper 事件循环的过程中，当出现空闲的时候，允许我们执行任务的一种机制，IdleHandler在looper里面的message处理完了的时候去调用 
###怎么使用
IdleHandler 被定义在 MessageQueue 中，它是一个接口. 定义时需要实现其 queueIdle() 方法。返回值为 true 表示是一个持久的 IdleHandler 会重复使用，返回 false 表示是一个一次性的 IdleHandler。IdleHandler 被 MessageQueue 管理，对应的提供了 addIdleHandler() 和 removeIdleHandler() 方法。将其存入mIdleHandle addIdleHandler() 和removeIdleHandler() 方法。将其存入 mIdleHandlers 这个 ArrayList 中。
###什么时候调用
既然 IdleHandler 主要是在 MessageQueue 出现空闲的时候被执行，那么何时出现空闲？
MessageQueue 是一个基于消息触发时间的优先级队列，所以队列出现空闲存在两种场景。
####1.就在MessageQueue的next方法里面，MessageQueue 为空，没有消息；
####2.MessageQueue 中最近需要处理的消息，是一个延迟消息（when>currentTime），需要滞后执行；

###使用场景
1.Activity启动优化：onCreate，onStart，onResume中耗时较短但非必要的代码可以放到IdleHandler中执行，减少启动时间

2.想要在一个View绘制完成之后添加其他依赖于这个View的View，当然这个用View#post()也能实现，区别就是前者会在消息队列空闲时执行

优化页面的启动,较复杂的view填充 填充里面的数据界面view绘制之前的话，就会出现以上的效果了，view先是白的，再出现. app的进程其实是ActivityThread,performResumeActivity先回调onResume ， 之后 执行view绘制的measure, layout, draw,也就是说onResume的方法是在绘制之前，在onResume中做一些耗时操作都会影响启动时间 把在onResume以及其之前的调用的但非必须的事件（如某些界面View的绘制）挪出来找一个时机（即绘制完成以后）去调用即可 

####Q：IdleHandler 有什么用？
1.IdleHandler 是 Handler 提供的一种在消息队列空闲时，执行任务的时机；

2.当 MessageQueue 当前没有立即需要处理的消息时，会执行 IdleHandler；
####Q：MessageQueue 提供了 add/remove IdleHandler 的方法，是否需要成对使用？
1.不是必须；

2.IdleHandler.queueIdle() 的返回值，可以移除加入 MessageQueue 的 IdleHandler；
####Q：当 mIdleHanders 一直不为空时，为什么不会进入死循环？
1.只有在 pendingIdleHandlerCount 为 -1 时，才会尝试执行 mIdleHander；

2.pendingIdlehanderCount 在 next() 中初始时为 -1，执行一遍后被置为 0，所以不会重复执行；
####Q：是否可以将一些不重要的启动服务，搬移到 IdleHandler 中去处理？
1.不建议；

2.IdleHandler 的处理时机不可控，如果 MessageQueue 一直有待处理的消息，那么 IdleHander 的执行时机会很靠后；
####Q：IdleHandler 的 queueIdle() 运行在那个线程？
1.陷进问题，queueIdle() 运行的线程，只和当前 MessageQueue 的 Looper 所在的线程有关；

2.子线程一样可以构造 Looper，并添加 IdleHandler；

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

