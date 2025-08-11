package com.zd.study.java;

import com.zd.study.java.bean.Dog;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class AbstractAndInterTest {
    public static void main(String[] args) {
        testYin();
    }
    public void test(){
        Dog dog = new Dog("kaka",3);
        dog.name = "haha";
    }

    public static void testYin(){
        Object strongRef = new Object();  // 强引用
        SoftReference<Object> softRef = new SoftReference<>(new Object());  // 软引用
        WeakReference<Object> weakRef = new WeakReference<>(new Object());  // 弱引用
        PhantomReference<Object> phantomRef = new PhantomReference<>(new Object(), new ReferenceQueue<>());  // 虚引用
        System.gc();  // 触发垃圾回收
        System.out.println("Strong Reference: " + strongRef);
        System.out.println("Soft Reference: " + softRef.get());
        System.out.println("Weak Reference: " + weakRef.get());
        System.out.println("Phantom Reference: " + phantomRef.get());
    }




}
