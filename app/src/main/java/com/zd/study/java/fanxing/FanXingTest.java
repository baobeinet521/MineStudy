package com.zd.study.java.fanxing;

import com.zd.study.java.bean.MyClass;
import com.zd.study.java.bean.Student;
import com.zd.study.java.bean.SubStudent;

import java.util.ArrayList;
import java.util.List;

public class FanXingTest {
    public static void main(String[] args) {
        test1();
    }

    /**
     *
     * 泛型的本质是参数化类型
     * 也就是说，泛型就是将所操作的数据类型作为参数的一种语法
     *
     * 通配符 ？：表示类型不确定，只能用于声明变量或者形参上，不能用在创建泛型类，泛
     * 型方法和接口上
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

        SubStudent<String> sunS = new SubStudent<>();
        sunS.setScore("subTest");

        list.add(student);
        list.add(sunS);

        System.out.println(list);
    }
}
