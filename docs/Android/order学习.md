查看任务栈信息
cmd进入命令窗口输入
adb shell dumpsys activity -p com.packagename.packagename > taskinfos.txt
此命令式将任务栈信息输出到文件taskinfos.txt中

如果在Android环境下直接输入一下命令打印栈信息
dumpsys activity -p com.zd.study