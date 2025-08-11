package com.zd.study.java.thread.practice;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTest {
    Executor e = new Executor() {
        @Override
        public void execute(Runnable command) {

        }
    };
    LinkedBlockingQueue a = new LinkedBlockingQueue<Runnable>(11);

//    Executors es = new Executors();

}
