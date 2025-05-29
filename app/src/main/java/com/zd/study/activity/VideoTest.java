package com.zd.study.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class VideoTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void test(){
//        VideoProcessor.processor(this)
//                .input("")
//                .output("outputVideoPath")
//                .outWidth(640)
//                .outHeight(480)
//                .process();
    }



    public static void main(String[] args) {
        // 确保在IO线程上发射数据
        Observable<String> observable1 = Observable.just("A", "B", "C")
                .subscribeOn(Schedulers.io());

        // 确保在IO线程上发射数据
        Observable<Integer> observable2 = Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io());

        // 确保在IO线程上订阅，以便接收数据
        Observable.zip(observable1, observable2, (str, num) -> str + num)
                .subscribeOn(Schedulers.io())
                // 可以在一个单独的线程（例如UI线程）上观察结果
                .observeOn(Schedulers.single())
                // 正常接收数据 // 错误处理
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace
                );
    }
}
