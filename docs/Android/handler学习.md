1、Handler Looper message三者之间的关系是什么？

Message:  需要传递的消息，可以传递数据；
MessageQueue: 消息队列，但是它的内部实现并不是用的队列，实际上是通过一个单链表的数据结构来维护消息列表，因为单链表在插入和删除上比较有优势。主要功能向消息池投递消息（MessageQueue.enqueueMessage）和取走消息池的消息（MessageQueue.next）
Handler: 消息辅助类，主要功能向消息池发送各种消息事件（Handler.sendMessage）和处理相应消息事件(Handler.handleMessage)
Looper:不断循环执行（Looper.loop），从MessageQueue中读取消息，按分发机制将消息分发给目标处理者

MessageQueue，Handler和Looper三者之间的关系：
每个线程中只能存在一个Looper,Looper是保存在ThreadLocal中的。主线程（UI线程）已经创建了一个Looper,所以主线程不需要在创建Looper，但是在其他线程中需要创建Looper。每个线程中可以有多个Handler，即一个Looper可以处理来自多个Handler的消息。Looper中维护一个MessageQueue，来维护消息队列，消息队列中的Message可以来自不同的Handler.

1. Handler 是如何与线程关联的？
Looper 提供了 Looper.prepare() 方法来创建 Looper ， 并且会借助
ThreadLocal 来实现与当前线程的绑定功能。 Looper.loop() 则会开始不断尝试从
MessageQueue 中获取 Message , 并分发给对应的 Handler
也就是说 Handler 跟线程的关联是靠 Looper 来实现的

2. Handler 发出去的消息是谁管理的？
  MessageQueue 管理消息
3. 消息又是怎么回到 handleMessage() 方法的？
  通过Looper切换线程
4. 线程的切换是怎么回事？
   
 Handler 发送的消息由 MessageQueue 存储管理， 并由 Looper 负责回调消息
到 handleMessage()。
线程的转换由 Looper 完成， handleMessage() 所在线程由 Looper.loop() 调用
者所在线程决定。

5、AsyncTask、HandlerThread、 Messenger、 IdleHandler 和 IntentService


6、Handler 引起的内存泄露原因以及最佳解决方案
Handler 允许我们发送延时消息， 如果在延时期间用户关闭了 Activity， 那么该
Activity 会泄露。
这个泄露是因为 Message 会持有 Handler， 而又因为 Java 的特性， 内部类会
持有外部类， 使得 Activity 会被 Handler 持有， 这样最终就导致 Activity 泄露。
解决该问题的最有效的方法是： 将 Handler 定义成静态的内部类， 在内部持有
Activity 的弱引用， 并及时移除所有消息。并且再在 Activity.onDestroy() 前移除消息mWorkHandler.removeCallbacksAndMessages(null);， 加一层保障，这样双重保障， 就能完全避免内存泄露了。
注意： 单纯的在 onDestroy 移除消息并不保险， 因为 onDestroy 并不一定执行。

7、主线程的 Looper 不允许退出，其实原因很简单， 主线程不允许退出， 退出就意味 APP 要挂。

8、创建 Message 实例的最佳方式
由于 Handler 极为常用， 所以为了节省开销， Android 给 Message 设计了回收
机制， 所以我们在使用的时候尽量复用 Message ， 减少内存消耗。
方法有二：
1. 通过 Message 的静态方法 Message.obtain(); 获取；
2. 通过 Handler 的公有方法 handler.obtainMessage(); 
  
  9、 知识点汇总
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

我们还有以下几种方法可以在子线程中进行 UI 操作：1. Handler 的 post()方法
2. View 的 post()方法
3. Activity 的 runOnUiThread()方法

