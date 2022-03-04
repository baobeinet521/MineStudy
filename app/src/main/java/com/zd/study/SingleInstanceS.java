package com.zd.study;

public class SingleInstanceS {

    //静态内部类的方式效果类似双检锁，但实现更简单。但这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
    //使用静态内部类的话。只有当静态内部类被使用的时候 才会加载 这时候才会初始化静态变量
    //无论懒汉式 饿汉式 或者静态内部类写法 都能通过发射构造多个实例 破坏单例模式呢
    //反射到你的构造器设置可访问为true 即可创建多个实例
    //但是枚举的话 就没办法反射调用构造器创建多个实例
    //既能实现懒加载 也能实现 线程安全
    private SingleInstanceS(){

    }

    public static class SingleInstanceSHolder{
        private static final SingleInstanceS instance = new SingleInstanceS();
    }

    public static SingleInstanceS getInstance(){
        return SingleInstanceSHolder.instance;
    }
}
