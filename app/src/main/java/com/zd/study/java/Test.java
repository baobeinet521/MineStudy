package com.zd.study.java;

public class Test {
    public static void main(String[] args) {
        int temp = 10;
        String str = "Hello";
        System.out.println("前  temp = " + temp + "  str = " + str);
        changeInt(temp);
        changeStr(str);
        System.out.println("后  temp = " + temp + " str = " + str);

        Student student = new Student(10);
        System.out.println(student.getAge());
        changeStudent(student);
        System.out.println(student.getAge());


    }

    public static void changeInt(int num){
        num = num + 20;
    }

    public static void changeStr(String strT){
        strT = "不知道是啥玩意";
    }

    public static void changeStudent(Student data){
//        data.setAge(20);
        data = new Student(30);
    }

}
