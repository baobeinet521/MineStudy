package com.zd.study.java.fanxing;

import com.zd.study.java.bean.MyClass;
import com.zd.study.java.bean.Student;
import com.zd.study.java.bean.SubStudent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FanXingTest {
    public static void main(String[] args) {
        test2();
    }

    /**
     *
     * 泛型的本质是参数化类型
     * 也就是说，泛型就是将所操作的数据类型作为参数的一种语法
     *
     * 通配符 ？：表示类型不确定，只能用于声明变量或者形参上，不能用在创建泛型类，泛
     * 型方法和接口上
     *
     *
     *
     * ? extends E 是 泛型 的上边界 ， ? super T 是 泛型的下边界
     *
     *
     *
     * 泛型分为:
     * 1 : 自定义泛型接口 interface Observer<T>
     * 2 : 泛型类 class ImplObserver<T> implements Observer<T>
     * 3 : 泛型方法 <T> Observer<T> call(T t)
     * 说一下泛型的作用域
     * 如果将泛型声明放在泛型接口,泛型类上,则该泛型在该类中就是确定的了,如果将泛型声
     * 明放在了泛型方法上,则该泛型只在该方法中有效,如果泛型方法上声明的泛型类型和类或
     * 接口中声明的泛型一致,则会在该方法中隐藏类或接口上的泛型
     */

    /**
     * 泛型嵌套
     */
    public static void test1(){
        Student<String> student = new Student<>();
        student.setScore("great");

        //泛型嵌套
        MyClass<Student<String>> cls = new MyClass<>();
        cls.setCls(student);

        Student<String> student2 = new Student<>();
        student2 = cls.getCls();

        System.out.println(student2.getScore());


        List<Student<String>> list = new ArrayList<>();

        SubStudent sunS = new SubStudent();
        sunS.setScore("subTest");

        list.add(student);
        list.add(sunS);

        System.out.println(list);




        /**
         * List < ? super C > 表示 list 里面存的是 C 和其父类，具体是啥不确定，只知道范
         * 围。
         */

        List<? super Student<String>> testSt1 = new ArrayList<>();
    }

    public static void test2(){
        /**
         * List < ? extends A > 表示 这个 list 里面存的是 A 的子类，具体是啥不知道，只
         * 知道范围！
         */

        List<Student> testAll = new ArrayList<>();
        Student st1 = new Student();
        st1.setScore("100");

        testAll.add(st1);

        List<SubStudent> testSt = new ArrayList<>();
        SubStudent subStudent = new SubStudent();
        subStudent.setScore("17");
        testSt.add(subStudent);
        testAll.addAll(testSt);

        for (int i = 0; i < testAll.size(); i++){
            System.out.println("test2  学生的分数  " + testAll.get(i).getScore());
        }

    }

    public static void test3(){

    }
    public static void test4(){
        Map<String, String> map = new HashMap<>();
        map.entrySet();
    }
}
