package com.zd.study.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    open fun testA(){
        System.out.println("BaseActivity  hahah   父类 执行setA（）=====")
    }


}