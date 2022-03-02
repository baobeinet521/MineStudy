package com.zd.study;

public class SingleInstanceD {
    public static SingleInstanceD mInstance;
    private SingleInstanceD(){

    }

    public static SingleInstanceD getInstance() {
        if (mInstance == null) {
            synchronized (SingleInstanceD.class) {
                if (mInstance == null) {
                    mInstance = new SingleInstanceD();
                }

            }
        }
        return mInstance;
    }



    public void testPrint(){
        System.out.println("测试一下。哈哈哈");
    }
}
