package com.zd.study.java.practice;

import com.zd.study.java.bean.DLinkedNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheNiuke {
    private Map<Integer, DLinkedNode> mHashMap = new HashMap();
    private int mCapacity;
    private DLinkedNode mTempHead;
    private DLinkedNode mTempTail;
    private int mSize = 0;

    public int[] LRU (int[][] operators, int k) {
        if(operators == null){
            int[] resultTemp = new int[1];
            resultTemp[0] = -1;
            return  resultTemp;
        }
        int[] result = new int[operators.length];
        this.mCapacity = k;
        mTempHead = new DLinkedNode();
        mTempTail = new DLinkedNode();
        mSize = 0;
        mTempHead.next = mTempTail;
        mTempTail.pre = mTempHead;
        int count = 0;
        for(int i = 0; i < operators.length; i++){
            if(operators[i][0] == 1){
                put(operators[i][1], operators[i][2]);
            }
            if(operators[i][0] == 2){
                count++;
                int res = get(operators[i][1]);
                result[i] = res;
            }
        }

        int[] resultTemp = new int[count];
        int countSize = 0;
        for(int i = 0; i < result.length; i++){
            if(result[i] != 0){
                resultTemp[countSize++] = result[i];
            }
        }
        return  resultTemp;
    }


    public int get(int key) {
        if(mHashMap.containsKey(key)){
            DLinkedNode node = mHashMap.get(key);
            removeNode(node);
            addNodeToHead(node);
            return mHashMap.get(key).value;
        }else {
            return -1;
        }

    }

    public void put(int key, int value) {
        //超过容量 删除尾部节点，并且把新该节点放在头部，没有超过容量直接把节点放在头部
        if(mHashMap.containsKey(key)){
            DLinkedNode node = mHashMap.get(key);
            removeNode(node);
            node.value = value;
            mHashMap.put(key, node);
            addNodeToHead(node);
        } else {
            DLinkedNode temp = new DLinkedNode(key, value);
            if(mSize >= mCapacity){
                mHashMap.remove(mTempTail.pre.key);
                removeTailNode();
                mHashMap.put(key,temp);
                addNodeToHead(temp);
            }else{
                addNodeToHead(temp);
                mHashMap.put(key, temp);
                mSize++;
            }
        }
    }

    public void removeTailNode(){
        mTempTail.pre.pre.next = mTempTail;
        mTempTail.pre = mTempTail.pre.pre;

    }

    public void addNodeToHead(DLinkedNode node){
        node.next = mTempHead.next;
        node.pre = mTempHead;
        mTempHead.next.pre = node;
        mTempHead.next = node;
    }

    public void removeNode(DLinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}
