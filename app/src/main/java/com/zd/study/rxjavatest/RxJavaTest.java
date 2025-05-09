package com.zd.study.rxjavatest;

import android.annotation.SuppressLint;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaTest {

    public static void main(String[] args) {
        testRxJava();
    }

    @SuppressLint("CheckResult")
    public static void testRxJava(){
        System.out.println("合并请求测试  testRxJava   ");
        // 确保在IO线程上发射数据
        Observable<Integer> observable1 = Observable.just(5)
                .subscribeOn(Schedulers.io());

        // 确保在IO线程上发射数据
        Observable<Integer> observable2 = Observable.just(1)
                .subscribeOn(Schedulers.io());

        // 确保在IO线程上订阅，以便接收数据
        Observable.zip(observable1, observable2, (str, num) ->{
                   int test = str + num;
                   System.out.println("合并请求结果   "   + test);
                   return test;
                })
                .subscribeOn(Schedulers.io())
                // 可以在一个单独的线程（例如UI线程）上观察结果
                .observeOn(Schedulers.single())
                // 正常接收数据 // 错误处理
                .subscribe(new Consumer<Integer>() {
                               @Override
                               public void accept(Integer integer) throws Throwable {
                                   System.out.println("合并请求观察到的结果   "   + integer);
                               }
                           }
                );
    }
}
