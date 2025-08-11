package com.zd.study.java.bean;

abstract public class Animal {
    public String TAG = "abstractAnimalTest";

    public String name;
    public int age;
    private String type;
    public Animal(String name, int age){
        this.name = name;
        this.age = age;
      /*  if(!TextUtils.isEmpty(name) && name.startsWith("hh")){
            type = "ceshi";
        }
        if(TextUtils.equals(type, "ceshi")){
            Log.d(TAG, "type  " + type);
        }*/
    }
}
