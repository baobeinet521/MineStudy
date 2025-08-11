package com.zd.study.kotlin

import com.zd.study.java.bean.Animal
import com.zd.study.java.bean.Dog

fun main(args: Array<String>){
    val TAG = "kotlinTest"
    val test = "zheng".let {
        true
        it.length
    }
    println(test)

    val test1 = "123".let {
        9999
        "[${it.length}]"
    }
    println(test1)
    println( "test   $test")
    println( "test1   $test1")
    val dog = Dog("kaka",4)
    println( dog.name)
    println( dog.age.toString())
    val dog1= dog.also {
        dog.name = "kekeaiai"
        dog.age = 3
    }
    println( dog1.name)
    println( dog1.age.toString())


}
