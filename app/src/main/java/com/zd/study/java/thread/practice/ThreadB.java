package com.zd.study.java.thread.practice;

public class ThreadB implements Runnable {

    private Object mObject;

    public ThreadB(Object object) {
        this.mObject = object;
    }

    @Override
    public void run() {
        synchronized (mObject) {
            for (int i = 0; i < 26; i++) {
                int data = i + 1;
                System.out.println("ThreadB ===  " + data);
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
