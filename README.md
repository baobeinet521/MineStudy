# Android学习笔记

## 一.Activity

### 1.Activity的启动流程

### 2.onSaveInstanceState(),onRestoreInstanceState的调用时机

### (1) onSaveInstanceState(Bundle outState)在什么时机会被调用呢？

当activity有可能被系统回收的情况下,而且是在onStop()之前。注意是有可能，如果是已经确定会被销毁，比如用户按下了返回键，或者调用了finish()方法销毁activity，则onSaveInstanceState不会被调用。

#### onSaveInstanceState(Bundle outState)会在以下情况被调用：

- 当用户按下HOME键时。
- 从最近应用中选择运行其他的程序时。
- 按下电源按键（关闭屏幕显示）时。
- 从当前activity启动一个新的activity时。
- 屏幕方向切换时(无论竖屏切横屏还是横屏切竖屏都会调用)。

在前4种情况下，当前activity的生命周期为： onPause -> onSaveInstanceState ->
onStop。

#### (2) onRestoreInstanceState什么时机被调用？

onRestoreInstanceState(Bundle
savedInstanceState)只有在activity确实是被系统回收，重新创建activity的情况下才会被调用。

#### 比如第5种情况屏幕方向切换时，activity生命周期如下：

onPause -> onStop-》onSaveInstanceState -> onDestroy -> onCreate ->
onStart -> onRestoreInstanceState -> onResume

在这里onRestoreInstanceState被调用，是因为屏幕切换时原来的activity确实被系统回收了，又重新创建了一个新的activity。

而按HOME键返回桌面，又马上点击应用图标回到原来页面时，activity生命周期如下：
onPause -> onSaveInstanceState -> onStop -> onRestart -> onStart ->
onResume

因为activity没有被系统回收，因此onRestoreInstanceState没有被调用。

如果onRestoreInstanceState被调用了，则页面必然被回收过，则onSaveInstanceState必然被调用过。

#### (3) onCreate()里也有Bundle参数，可以用来恢复数据，它和onRestoreInstanceState有什么区别？

因为onSaveInstanceState
不一定会被调用，所以onCreate()里的Bundle参数可能为空，如果使用onCreate()来恢复数据，一定要做非空判断。
而onRestoreInstanceState的Bundle参数一定不会是空值，因为它只有在上次activity被回收了才会调用。
而且onRestoreInstanceState是在onStart()之后被调用的。有时候我们需要onCreate()中做的一些初始化完成之后再恢复数据，用onRestoreInstanceState会比较方便。

下面是官方文档对onRestoreInstanceState的说明：

注意这个说明的最后一句是什么意思？

to allow subclasses to decide whether to use your default
implementation.
它是说，用onRestoreInstanceState方法恢复数据，你可以决定是否在方法里调用父类的onRestoreInstanceState方法，即是否调用super.onRestoreInstanceState(savedInstanceState);
而用onCreate()恢复数据，你必须调用super.onCreate(savedInstanceState);


### 3.activity的启动模式和使用场景

#### (1)基本描述

- standard：标准模式：如果在mainfest中不设置就默认standard；每次启动时都会创建一个新的实例
- singleTop：栈顶复用模式：使用场景：展示推送过来的消息，如果栈中没有activity创建并入栈，如果栈中已经有Activity且此activity在栈顶直接复用，如果栈中有Activity但是并不在栈顶新建activity并入栈，与standard相比栈顶复用可以有效减少activity重复创建对资源的消耗，但是这要根据具体情况而定，不能一概而论；
- singleTask：栈内单例模式，使用场景：程序入口等启动页面，栈内只会有一个activity实例，栈内已存activity实例，如果在栈顶则直接复用，不在栈顶，会把该activity上所有activity实例出栈以后再复用此activity
- singleInstance
  :堆内单例：使用场景：完全独立的，类似闹钟的提示，整个手机操作系统里面只有一个实例存在就是内存单例；若该实例不存在,则要启动一个新activity实例,并且会存在于一个单独的任务栈中

#### (2)Intent中标志位设置启动模式

启动模式可以在AndroidMainfest的xml文件中进行配置，GoogleAndroid团队同时提供另种级别更高的设置方式，即通过Intent.setFlags(int
flags)设置启动模式；

- FLAG_ACTIVITY_CLEAR_TOP : 等同于mainfest中配置的singleTask；
- FLAG_ACTIVITY_SINGLE_TOP: 同样等同于mainfest中配置的singleTop;
- FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS:
  其对应在AndroidManifest中的属性为android:excludeFromRecents=“true”,当用户按了“最近任务列表”时候,该Task不会出现在最近任务列表中，可达到隐藏应用的目的。
- FLAG_ACTIVITY_NO_HISTORY:
  对应在AndroidManifest中的属性为:android:noHistory=“true”，这个FLAG启动的Activity，一旦退出，它不会存在于栈中。
- FLAG_ACTIVITY_NEW_TASK:
  这个属性需要在被start的目标Activity在AndroidManifest.xml文件配置taskAffinity的值【必须和startActivity发起者Activity的包名不一样，如果是跳转另一个App的话可以taskAffinity可以省略】，则会在新标记的Affinity所存在的taskAffinity中压入这个Activity。

### (二)、taskAffinity属性

taskAffinity只有和SingleTask启动模式匹配使用时，启动的Activity才会运行在名字和taskAffinity相同的任务栈中

- 1.如果不指定taskAffinity属性，应用程序的所有Activity都存放于默认task（single
  instance启动的activity除外，因为single instance启动的activity独占一个task）
- 2.指定taskAffinity，只有当Activity设置FLAG_ACTIVITY_NEW_TASK或者在AndroidMainfest.xml启动模式设置为singleTask才起作用，否则不起作用。

#### Activity四种启动模式常见使用场景

-![activity启动模式场景](file:///C:\zd\zdgitworksapce\MineStudy\docs\img\activity启动模式\activity启动模式使用场景.png)

### 4.Activity A跳转Activity B，再按系统返回键，生命周期执行的顺序

#### （1）Activity A，Activity B都是启动模式都是标准模式（standard）

A onCreate-->A onStart()-->A onResume()-->A onpasue()-->B onCreate()-->B
onStart()-->B onResume()-->A onStop()-->B onPause()-->A onRestart()-->A
onstart()-->A onResume()--->B onStop()-->B onDestory()

#### 5.横竖屏切换,按home键,按返回键,锁屏与解锁屏幕,跳转透明Activity界面,启动一个 Theme 为 Dialog 的 Activity，弹出Dialog时Activity的生命周期

##### Android4.0以前配置横竖屏切换属性是：android:configChanges="keyboardHidden|orientation”

##### Android4.0以后配置横竖屏切换属性是：android:configChanges="keyboardHidden|orientation|screenSize"

1、未配置android:configChanges="keyboardHidden|orientation|screenSize"切换横竖屏生命周期
onCreate()-->onStart()-->onResume()-->onPasue()-->切换横竖屏onStop()--onSaveInstanceState()-->onDestory()-->onCreate()-->onStart()--onRestoreInstanceState()-->onResume()

2、配置android:configChanges="keyboardHidden|orientation|screenSize"切换横竖屏生命周期

onCreate()-->onStart()-->onResume()-->切换竖屏onConfigurationChanged()切换横屏onConfigurationChanged()
熄屏
onCreate()-->onStart()-->onResume()熄屏-->onPasue()-->onStop()-->onSaveInstanceState()
解锁屏幕
解锁屏幕后如果页面不可见执行的生命周期(如锁屏后网易云音乐界面在前台，解锁后可见页面也是网易云音乐)onRestart（）-->onStart()
解锁屏幕后如果页面可见执行的生命周期（解锁后可见页面是网易云音乐，右滑关闭网易云页面，app页面可见执行的生命周期）onRestart（）-->onStart()-->onResume()


按home键 执行的生命周期 onPause()-->onstop()--->onSaveInstanceState()
从最近任务选择app执行的生命周期是：onRestart()-->onstart()-->onResume()

按返回键 onPasue()-->onStop()-->onDestory()

启动一个 Theme 为 Dialog 的 Activity，弹出Dialog时Activity的生命周期
onCreate()-->onStart()-->onResume()--->启动一个 Theme 为 Dialog 的
Activity -->onPasue()按返回键--->onResume()

#### 6.onStart 和 onResume、onPause 和 onStop 的区别

##### onStart

onCreate或者onRestart方法执行后会调用此方法，该方法表明Activity准备展示给用户了

This is a good place to begin drawing visual elements, running
animations 这是开始绘制视觉元素、运行动画的好地方

可以在这个方法里调用finish，会跳过onResume, onPause直接调用onStop Dispatch
onStart() to all fragments.

##### onResume

在onRestoreInstanceState, onRestart,
或者onPause之后被调用，以便页面开始和用户进行交互

这时activity已经激活并且准备接受输入内容，此时activity在栈顶并且用户可见

Build.VERSION_CODES.Q（Android
10）之前，这也是尝试打开独占访问设备或访问单例资源的好地方（如相机设备等），Android
10以后，Android具有多个任务同时工作的情况，所以多个Activity的onResume可能同时恢复工作，但是要判断谁在栈顶可以通过onTopResumedActivityChanged(boolean)来判断
将onResume()分派fragments，
注意：为了更好的与旧版本的平台进行交互操作，再此调用fragments attached
到activity不会恢复

##### onPause

当用户不在主动与activity交互时调用，但是仍然在屏幕可见，与之相对应的是重新开始

当在activity A页面启动activity
B时，将在A上调用此回调，在A的onPause返回之前，不会创建B，因此请确保不要在此处执行任何冗长的操作

此回调主要用于保存activity
正在编辑的任何持久状态，向用户提供“原地编辑”模型，并确保在没有足够资源启动新活动而不首先终止此活动时不会丢失任何内容。这也是一个关闭一些耗时CPU较高的工作的好地方，以便尽可能快地切换到下一个activity
Build.VERSION_CODES.Q（Android
10）之前，这也是关闭独占访问设备或释放单例资源的好地方（如相机设备等），Android
10以后，Android具有多个任务同时工作的情况，所以多个Activity的onResume可能同时恢复工作，但是要判断谁在栈顶可以通过onTopResumedActivityChanged(boolean)来判断
如果activity在栈顶，在接收到这个调用以后，你一般将会收到onstop调用（在下一个activity已经被恢复并且显示）。然而在某些情况下，会直接回调onResume，而不会进入stopped状态。在某些情况下，当处于多窗口模式时，活动也可以处于暂停状态，用户仍然可以看到onStop
用户不在可见时调用，方法后续可能会执行onRestart,
onDestroy或者什么也没有，具体取决于后面用户的activity。这里是一个停止刷新ui、运行动画和其他视觉效果的好地方


## 二、 事件分发

[事件分发学习网站](https://www.cnblogs.com/chengxuyinli/p/9979826.html)


- MotionEvent
- ACTION_DOWN = 0
- ACTION_UP = 1
- ACTION_MOVE = 2
- ACTION_CANCEL = 3

- 事件分发的完整流程
- ![viewGroup的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/完整流程.png)

#### activity  所有事件分发方法都调用父类方法

![activity的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/normal_process.png)


#### dispatchTouchEvent 方法返回true/false

activity 的 dispatchTouchEvent 方法返回true/false 事件直接被消费

- activity 的 dispatchTouchEvent 方法返回true

![activity的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/activity_disPatch_return_true.png)

- activity 的 dispatchTouchEvent 方法返回false

![activity的dispatchTouchEvent返回false](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/activity_disPatch_return_false.png)

### 总结 ###

activity的dispatchTouchEvent返回true和false事件都会被消费

***

- viewGroup 的 dispatchTouchEvent 方法返回true,事件直接消费

![viewGroup的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/viewGroup_disPatch_return_true.png)

- viewGroup 的 dispatchTouchEvent 方法返回false

*返回false会询问父控件onTouchEvent是否消费事件*

![viewGroup的dispatchTouchEvent返回false](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/viewGroup_disPatch_return_false.png)

- ViewGroup 的 onInterceptTouchEvent 返回false

![viewGroup的onInterceptTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/viewGroup_intercept_return_false.png)

- ViewGroup 的 onInterceptTouchEvent super.onInterceptTouchEvent(ev)
  TouchEvent 方法返回true

![view的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/viewGroup_superonintercept_onTouchEvent_return_true.png)

- ViewGroup 的 onInterceptTouchEvent return true TouchEvent
  方法返回super.TouchEvent()

![view的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/viewGroup_onIntercept_return_true_touchevent_super.png)

- ViewGroup 的 onInterceptTouchEvent return true TouchEvent 方法返回true

![view的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/viewGroup_onIntercept_return_true_touchevent_return_true.png)

### 总结 ###

viewGroup的dispatchTouchEvent返回false，事件会被传递到父控件的OnTouchEvent

***

- View 的 dispatchTouchEvent 方法返回true 事件直接被消费

![view的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/view_disPatch_return_true.png)

- View 的 dispatchTouchEvent 方法返回false

  ![view的dispatchTouchEvent返回true](file:///C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/view_disPatch_return_false.png)

- View 的 TouchEvent 方法返回true,view消费事件

![view的dispatchTouchEvent返回true](C:/zd/zdgitworksapce/MineStudy/docs/img/事件分发/view_touch_event_return_true.png)

### 总结 ###

view的dispatchTouchEvent返回false，事件会被传递到父控件的OnTouchEvent

#### OnTouch(),OnTouchEvent(),Onclick()方法的优先级

onTouch>onTouchEvent>onClick

## 三、Service

![sevice官网](https://developer.android.com/guide/components/services?hl=zh-cn)

Service
是一种可在后台执行长时间运行操作而不提供界面的应用组件。服务可由其他应用组件启动，而且即使用户切换到其他应用，服务仍将在后台继续运行。此外，组件可通过绑定到服务与之进行交互，甚至是执行进程间通信
(IPC)。例如，服务可在后台处理网络事务、播放音乐，执行文件 I/O
或与内容提供程序进行交互。

#### 1、后台 Service 限制

系统可以区分前台和后台应用。 （用于 Service
限制目的的后台定义与内存管理使用的定义不同；一个应用按照内存管理的定义可能处于后台，但按照能够启动
Service 的定义又处于前台。）如果满足以下任意条件，应用将被视为处于前台：

具有可见 Activity（不管该 Activity 已启动还是已暂停）。 具有前台 Service。
另一个前台应用已关联到该应用（不管是通过绑定到其中一个
Service，还是通过使用其中一个内容提供程序）。 例如，如果另一个应用绑定到该应用的
Service，那么该应用处于前台： IME 壁纸 Service 通知侦听器 语音或文本 Service
如果以上条件均不满足，应用将被视为处于后台

**请注意**：这些应用不会对绑定 Service 产生任何影响。 如果您的应用定义了绑定
Service，则不管应用是否处于前台，其他组件都可以绑定到该 Service。

处于前台时，应用可以自由创建和运行前台与后台 Service。
进入后台时，在一个持续数分钟的时间窗内，应用仍可以创建和使用 Service。
在该时间窗结束后，应用将被视为处于空闲状态。 此时，系统将停止应用的后台
Service，就像应用已经调用 Service 的 Service.stopSelf() 方法一样。

在这些情况下，后台应用将被置于一个临时白名单中并持续数分钟。
位于白名单中时，应用可以无限制地启动 Service，并且其后台 Service 也可以运行。
处理对用户可见的任务时，应用将被置于白名单中，例如：

1. 处理一条高优先级 Firebase 云消息传递 (FCM)消息。
2. 接收广播，例如短信/彩信消息。
3. 从通知执行 PendingIntent。
4. 在 VPN 应用将自己提升为前台进程前开启 VpnService。

**请注意**： IntentService 是一项 Service，因此其遵守针对后台 Service 的新限制。
因此，许多依赖 IntentService 的应用在适配 Android 8.0 或更高版本时无法正常工作。
出于这一原因，Android 支持库
26.0.0 引入了一个新的JobIntentService类，该类提供与 IntentService 相同的功能，但在
Android 8.0 或更高版本上运行时使用作业而非 Service。

在很多情况下，您的应用都可以使用 JobScheduler 作业替换后台 Service。
例如，CoolPhotoApp
需要检查用户是否已经收到好友共享的照片，即使该应用未在前台运行也需如此。
之前，应用使用一种会检查其云存储的后台 Service。 为了迁移到 Android 8.0（API 级别
26），开发者使用一个计划作业替换了这种后台
Service，该作业将按一定周期启动，查询服务器，然后退出。

在 Android 8.0 之前，创建前台 Service 的方式通常是先创建一个后台
Service，然后将该 Service 推到前台。 Android 8.0
有一项复杂功能：系统不允许后台应用创建后台 Service。 因此，Android 8.0
引入了一种全新的方法，即 startForegroundService()，以在前台启动新 Service。
在系统创建 Service 后，应用有五秒的时间来调用该 Service
的 startForeground() 方法以显示新 Service 的用户可见通知。
如果应用在此时间限制内未调用 startForeground()，则系统将停止此 Service
并声明此应用为 ANR


![service生命周期](file:///C:\zd\zdgitworksapce\MineStudy\docs\img\service\service_lifecycle.png)

### 1.service 的生命周期，两种启动方式的区别

1. 如果组件通过调用 startService() 启动服务（这会引起对 onStartCommand() 的调用），则服务会一直运行，直到其使用 stopSelf() 自行停止运行，或由其他组件通过调用 stopService() 将其停止为止。

2. 如果组件通过调用 bindService() 来创建服务，且未调用 onStartCommand()，则服务只会在该组件与其绑定时运行。当该服务与其所有组件取消绑定后，系统便会将其销毁。


只有在内存过低且必须回收系统资源以供拥有用户焦点的 Activity 使用时，Android
系统才会停止服务。如果将服务绑定到拥有用户焦点的
Activity，则它其不太可能会终止；如果将服务声明为在前台运行，则其几乎永远不会终止。如果服务已启动并长时间运行，则系统逐渐降低其在后台任务列表中的位置，而服务被终止的概率也会大幅提升—如果服务是启动服务，则您必须将其设计为能够妥善处理系统执行的重启。如果系统终止服务，则其会在资源可用时立即重启服务，但这还取决于您从 onStartCommand() 返回的值


