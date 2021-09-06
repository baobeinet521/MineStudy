package com.zd.study.utils;

public class Utils{
    public static String getPid(){
        int pid = android.os.Process.myPid();
        long threadId = Thread.currentThread().getId();
        int threadId2 = android.os.Process.myTid();
        return "进程id： " + pid + ", 线程Id = " + threadId + ", 线程Id2 = " + threadId2;
    }
}
