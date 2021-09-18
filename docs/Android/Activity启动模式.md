一、 Activity 四种启动模式：
(一)、基本描述
1. standard： 标准模式：如果在 mainfest 中不设置就默认 standard；standard
就是新建一个 Activity 就在栈中新建一个 activity 实例；
2. singleTop： 栈顶复用模式：与 standard 相比栈顶复用可以有效减少 activity
重复创建对资源的消耗，但是这要根据具体情况而定，不能一概而论；
3. singleTask： 栈内单例模式，栈内只有一个 activity 实例，栈内已存 activity
实例，在其他 activity 中 start 这个 activity，Android 直接把这个实例上
面其他 activity 实例踢出栈 GC 掉；
4. singleInstance :堆内单例：整个手机操作系统里面只有一个实例存在就是内
存单例；
在 singleTop、singleTask、singleInstance 中如果在应用内存在
Activity 实例，并且再次发生 startActivity(Intent intent)回到 Activity
后,由于并不是重新创建 Activity 而是复用栈中的实例，因此 Activity
再 获 取 焦 点 后 并 没 调 用 onCreate 、 onStart ， 而 是 直 接 调 用 了
onNewIntent(Intent intent)函数；
(二)、taskAffinity 属性
taskAffinity 属性和 Activity 的启动模式息息相关，而且 taskAffinity 属性比较
特殊，在普通的开发中也是鲜有遇到，但是在有些特定场景下却有着出其不意的
效果。
taskAffinity 是 Activity 在 mainfest 中配置的一个属性，暂时可以理解为：
taskAffinity 为宿主 Activity指定了存放的任务栈[不同于 App 中其他的 Activity
的栈]，为 activity 设置 taskAffinity 属性时不能和包名相同，因为 Android 团
队为 taskAffinity 默认设置为包名任务栈。taskAffinity 只有和 SingleTask 启动模式匹配使用时，启动的 Activity 才会运
行在名字和 taskAffinity 相同的任务栈中。
(三)、Intent 中标志位设置启动模式
在上文中的四种模式都是在 mainfest 的 xml 文件中进行配置的，
GoogleAndroid 团队同时提供另种级别更高的设置方式，即通过
Intent.setFlags(int flags)设置启动模式；
1. FLAG_ACTIVITY_CLEAR_TOP : 等 同 于 mainfest 中 配 置 的
singleTask，没啥好讲的；
2. FLAG_ACTIVITY_SINGLE_TOP: 同 样 等 同 于 mainfest 中 配 置 的
singleTop;
3. FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS: 其 对 应 在
AndroidManifest 中的属性为 android:excludeFromRecents=“true”,
当用户按了“最近任务列表”时候,该 Task 不会出现在最近任务列表中，
可达到隐藏应用的目的。
4. FLAG_ACTIVITY_NO_HISTORY: 对应在 AndroidManifest 中的属性
为:android:noHistory=“true”，这个 FLAG 启动的 Activity，一旦退出，
它不会存在于栈中。
5. FLAG_ACTIVITY_NEW_TASK: 这 个 属 性 需 要 在 被 start 的 目 标 Activity 在
AndroidManifest.xml 文件配置 taskAffinity 的值【必须和 startActivity
发起者 Activity 的 包 名 不 一 样 ， 如 果 是 跳 转 另 一 个 App 的 话taskAffinity 可以省略】，则会在新标记的 Affinity 所存在的 taskAffinity中压入这个 Activity。



1.Intent 属性Intent 的属性有：component(组件)、action、category、data、type、extras、
flags；所有的属性也是各显神通，满足开发者的各种需要满足不同场景；
component: 显然就是设置四大组件的，将直接使用它指定的组件，借助这一
属性可以实现不同应用组件之间通讯；
action： 是一个可以指定目标组件行为的字符串，开发人员可以自定义 action
通过匹配 action 实现组件之间的隐士跳转，当然 Android 系统也已经预定部分
String 作为系统应用 Action，例如打开系统设置页面等等；
data：通常是 URI 类型或者 MIME 类型格式定义的操作数据；表示与动作要操纵的数据
![enter description here](./images/微信图片_20210910165428.png)

Category： 属性用于指定当前动作（Action）被执行的环境；
type： 对于 data 范例的描写；
extras 和 flags 这两个太熟悉了就不在重复；


Activity生命周期：
跳转activity，执行
2021-09-10 17:34:32.497 2968-2968/com.zd.study D/LifeCycleActivity: ------------onCreate---------
2021-09-10 17:34:32.503 2968-2968/com.zd.study D/LifeCycleActivity: ------------onStart---------
2021-09-10 17:34:32.510 2968-2968/com.zd.study D/LifeCycleActivity: ------------onResume---------
2021-09-10 17:34:32.550 2968-2968/com.zd.study D/LifeCycleActivity: ------------onWindowFocusChanged---------hasFocus = true

锁屏：
2021-09-10 17:34:50.373 2968-2968/com.zd.study D/LifeCycleActivity: ------------onPause---------
2021-09-10 17:34:50.557 2968-2968/com.zd.study D/LifeCycleActivity: ------------onWindowFocusChanged---------hasFocus = false

开启屏幕：
2021-09-10 17:35:16.840 2968-2968/com.zd.study D/LifeCycleActivity: ------------onRestart---------
2021-09-10 17:35:16.852 2968-2968/com.zd.study D/LifeCycleActivity: ------------onStart---------
2021-09-10 17:35:16.857 2968-2968/com.zd.study D/LifeCycleActivity: ------------onResume---------
2021-09-10 17:35:17.566 2968-2968/com.zd.study D/LifeCycleActivity: ------------onWindowFocusChanged---------hasFocus = true

切换竖屏（没有配置android:configChanges属性）
2021-09-10 17:35:52.214 2968-2968/com.zd.study D/LifeCycleActivity: ------------onPause---------
2021-09-10 17:35:52.215 2968-2968/com.zd.study D/LifeCycleActivity: ------------onDestroy---------
2021-09-10 17:35:52.250 2968-2968/com.zd.study D/LifeCycleActivity: ------------onCreate---------
2021-09-10 17:35:52.271 2968-2968/com.zd.study D/LifeCycleActivity: ------------onStart---------
2021-09-10 17:35:52.272 2968-2968/com.zd.study D/LifeCycleActivity: ------------onRestoreInstanceState---------
2021-09-10 17:35:52.303 2968-2968/com.zd.study D/LifeCycleActivity: ------------onResume---------
2021-09-10 17:35:52.784 2968-2968/com.zd.study D/LifeCycleActivity: ------------onWindowFocusChanged---------hasFocus = true

AndroidManifest.xml中设置android:configChanges="orientation|screenSize“横竖屏切换
2021-09-10 17:44:35.085 7637-7637/com.zd.study D/LifeCycleActivity: ------------onConfigurationChanged---------

注意：自从Android 3.2（API 13），在设置Activity的android:configChanges="orientation|keyboardHidden"后，还是一样会重新调用各个生命周期的。因为screen size也开始跟着设备的横竖切换而改变。所以，在AndroidManifest.xml里设置的MiniSdkVersion和 TargetSdkVersion属性大于等于13的情况下，如果你想阻止程序在运行时重新加载Activity，除了设置"orientation"，你还必须设置"ScreenSize"


