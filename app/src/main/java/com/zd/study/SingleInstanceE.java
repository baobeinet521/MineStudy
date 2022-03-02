package com.zd.study;

public class SingleInstanceE {
    public static SingleInstanceE mSingleInstanceE = new SingleInstanceE();

    private SingleInstanceE(){

    }
    public static SingleInstanceE getInstance(){
      return mSingleInstanceE;
    }
}
