package com.zd.study.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zd.study.R
import io.reactivex.rxjava3.core.Flowable

class StudyKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_layout)
//        test_btn.setOnClickListener{
//            val c:Int = sum(2 ,3)
//            println("两个整数的和是c是  $c")
//            val  d = sumOne(6 , 7)
//            println("两个整数的和是d是  $d")
//
//            val  e = sumTwo(10 , 4)
//            println("两个整数的和是e是  $e")
//
//            printSum(40,50)
//
//            vars(1,2,3,4,5)
//            var sumLambda:(Int, Int) -> Int = {x,y -> x + y}
//            println(sumLambda(10, 20))
//
//        }

        Flowable.just("Hello world").subscribe(System.out::println);
    }

    private fun sum(a:Int, b:Int):Int{
        return a + b
    }

    private fun sumOne(a:Int, b:Int) = a + b

    public fun sumTwo(a:Int, b:Int):Int = a + b

    fun printSum(a:Int, b:Int){
        println(a + b)

    }

    fun vars(vararg v:Int){
        for (vt in  v){
            println(vt)
        }

    }

}