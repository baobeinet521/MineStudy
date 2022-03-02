package com.zd.study;

public class SingleInstanceL {
    public static SingleInstanceL mSingleInstance;

    private SingleInstanceL(){

    }

    public static SingleInstanceL getInstance(){
        if(mSingleInstance == null){
            mSingleInstance = new SingleInstanceL();
        }
        return mSingleInstance;
    }
}
