package com.zd.study.zdstrxjava

import com.zd.study.java.bean.Student
import com.zd.study.java.bean.Teacher
import com.zd.study.zdstrxjava.inter.Action
import com.zd.study.zdstrxjava.inter.Func1
import com.zd.study.zdstrxjava.inter.Observable

class RxStudyTest {
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            test1()
        }

        fun test(){
            //将泛型声明在接口上或声明在类上
//            val observer: Observable<Student<*>> = ObservableImpl()
            val create: Observable<Student<String>> = ObservableImpl.create(Student())
            val student = create.call()

            //将泛型声明在方法上
            val observer2 = Observable2Impl()
            val student2: Student<String> = observer2.call(Student())
        }

        fun test1(){
            val student = Student<String>()
            println("创建好student  $student")

            val teacher = Teacher()
            println("创建好teacher  $teacher")
            ObservableImpl.create(student)
                .map(object: Func1<Student<String>, Teacher>{
                    override fun call(t: Student<String>?): Teacher {
                        println("student hashcode : $t")
                        println("teacher hashcode : $teacher")
                        return teacher
                    }

                })
                ?.doNext(object: Action<Teacher>{
                    override fun callAction(t: Teacher?) {
                        println("teacher hashcode2 : $t")
                    }

                })

        }
    }
}