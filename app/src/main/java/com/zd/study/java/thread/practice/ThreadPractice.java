package com.zd.study.java.thread.practice;

public class ThreadPractice {

    public static void main(String[] args) {
        Object mObject = new Object();

        Runnable runnableA = new ThreadA(mObject);
        Thread threadA = new Thread(runnableA);
        threadA.start();

        Runnable runnableB = new ThreadB(mObject);
        Thread threadB = new Thread(runnableB);
        threadB.start();
    }
}
