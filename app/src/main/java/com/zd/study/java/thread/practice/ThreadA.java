package com.zd.study.java.thread.practice;

public class ThreadA implements Runnable {
    private Object mObject;
    public ThreadA(Object object) {
        this.mObject = object;
    }
    @Override
    public void run() {
        synchronized (mObject) {
            for (int i = 0; i < 26; i++) {
                char data = (char) ('A' + i);
                System.out.println("ThreadA ===  " + data);
                mObject.notifyAll();
                try {
                    mObject.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.toString());

                }
            }
        }

    }
}
