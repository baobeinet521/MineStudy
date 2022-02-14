package com.zd.study.java.bean;

public class DLinkedNode {
    public int key;
    public int value;
    public DLinkedNode pre;
    public DLinkedNode next;

    public DLinkedNode(){

    }

    public DLinkedNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}
