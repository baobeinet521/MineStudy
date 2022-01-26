package com.zd.study.java.practice;

import com.zd.study.java.bean.Car;

import java.lang.ref.WeakReference;

public class TestWeakReference {
    /**
     * WeakReference的一个特点是它何时被回收是不可确定的, 因为这是由GC运行的不确定性所确定的.
     * 所以, 一般用weak reference引用的对象是有价值被cache, 而且很容易被重新被构建, 且很消耗内存的对象
     *
     * 运行一会以后   object会被回收，因为 object被弱引用持有，且没有被使用，所以只要GC运行，被弱引用持有的对象就回被回收
     * @param args
     */
    public static void main(String[] args) {
        Car car = new Car(22000,"silver");

        WeakReference weakCar = new WeakReference(car);

        int i=0;

        while(true){
            //如果加上这句，weak reference指向的object就不会被回收了. 因为还有一个strong reference car 指向它.
            // 去掉这句，运行一段时间  object Car就会被回收
            System.out.println("here is the strong reference 'car' "+car);

            if(weakCar.get()!=null){
                i++;

                System.out.println("Object is alive for "+i+" loops - "+weakCar);

            }else{
                System.out.println("Object has been collected.");

                break;

            }

        }

    }
}
