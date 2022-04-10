package com.zd.study.java.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache2 {

    HashMap<Integer, Integer> map = new LinkedHashMap<>();
    int count;

    public LRUCache2(int capacity) {
        count = capacity;
    }

    public int get(int key) {

        if (map.containsKey(key)) {
            Integer value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {

        if (map.keySet().contains(key)) {
            map.remove(key);
        } else if (map.size() == count){
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
//            iterator.next();
            iterator.remove();
        }
        map.put(key, value);
    }
}
