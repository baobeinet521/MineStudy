package com.zd.frameworkmodule.widget;

import android.util.Log;

import com.zd.frameworkmodule.inter.IAnimalInterface;

public class AnimationImageView {
    private IAnimalInterface mBiz;
    public AnimationImageView(IAnimalInterface biz){
        this.mBiz = biz;
    }
    public void deal(){
        if (mBiz != null){
           String name = mBiz.getAnimalName();
           Log.d("frameworkmodule", "AnimationImageView   " + name);
        }
    }
}
