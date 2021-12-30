## 三、Service

[sevice官网](https://developer.android.com/guide/components/services?hl=zh-cn)

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