package com.zd.study.java.practice;

import com.zd.study.java.bean.DLinkedNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache{
    private Map<Integer, DLinkedNode> mHashMap = new HashMap();
    private int mCapacity;
    private DLinkedNode mTempHead;
    private DLinkedNode mTempTail;
    private int mSize = 0;

    public LRUCache(int capacity) {
        this.mCapacity = capacity;
        mTempHead = new DLinkedNode();
        mTempTail = new DLinkedNode();

        mTempHead.next = mTempTail;
        mTempTail.pre = mTempHead;
    }

    public int get(int key) {
        if(mHashMap.containsKey(key)){
            //todo(删掉该节点，并且把节点移到头部)
            return mHashMap.get(key).value;
        }else {
            return -1;
        }

    }

    public void put(int key, int value) {
        DLinkedNode temp = new DLinkedNode(key, value);
        //超过容量 删除尾部节点，并且把新该节点放在头部，没有超过容量直接把节点放在头部
        if(mHashMap.containsKey(key)){
            mHashMap.put(key, temp);
            removeNode(temp);
            addNodeToHead(temp);
        } else {
            if(mSize > mCapacity){
                removeTailNode(mTempTail.pre);
                mHashMap.remove(mTempTail.pre.key);
                addNodeToHead(temp);
                mSize--;
            }else{
                addNodeToHead(temp);
                mHashMap.put(key, temp);
                mSize++;
            }
        }
    }

    public void removeTailNode(DLinkedNode node){
        mTempTail.pre.pre.next = mTempTail;
        mTempTail.pre = mTempTail.pre.pre;

    }

    public void addNodeToHead(DLinkedNode node){
        node.next = mTempHead.next.next;
        node.pre = mTempHead;
        mTempHead.next.next.pre = node;
        node.pre = mTempHead;
    }

    public void removeNode(DLinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;

    }
}
