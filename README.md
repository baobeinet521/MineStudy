# Android学习笔记
## 事件分发学习
###插入链接
[事件分发](https://www.cnblogs.com/chengxuyinli/p/9979826.html)

- MotionEvent
 - ACTION_DOWN = 0
 - ACTION_UP = 1
 - ACTION_MOVE = 2
 - ACTION_CANCEL = 3

dispatchTouchEvent 方法不继承父类方法（super.dispatchTouchEvent(ev)）直接返回false或者true事件将停止分发
