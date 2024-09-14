package com.zd.study.java.DesignPatterns.FactoryModule

import android.util.Log
import com.zd.study.java.DesignPatterns.FactoryModule.inter.Animal

class Dog: Animal {
    override fun speak() {
        Log.d("ceshi", "小狗汪汪")
    }
}