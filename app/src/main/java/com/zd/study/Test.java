package com.zd.study;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class Test implements Parcelable {
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

    protected Test(Parcel in) {
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static class MyHandler extends Handler{
        private WeakReference<Context> mContext;
        public MyHandler(Context context){
           mContext = new WeakReference<Context>(context);

        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }
    //静态内部类
    public static class TestA{
        public void testAa(){
            System.out.println("----静态内部类----");
        }

    }

    //成员内部类
    class Holder{
        public void test(){
            System.out.println("----成员内部类----");
        }
    }

    public void main(String[] args) {
        TestA testA = new TestA();
        Holder holder = new Holder();
    }


}
