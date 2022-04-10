package com.zd.study.java.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LRUCache {

    List<Integer> list = new ArrayList<>();
    HashMap<Integer, Integer> map;
    int count;


    public LRUCache(int capacity) {
        count = capacity;
        map = new HashMap();
    }

    public int get(int key) {
        if (map == null)
            return -1;

        if (map.containsKey(key)) {
            refresh(key);
            return map.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map == null)
            return;

        if (map.containsKey(key)) {
            refresh(key);
            map.put(key, value);
        } else {
            if (list.size() == count) {
                map.remove(list.get(0));
                list.remove(0);
            }
            list.add(key);
            map.put(key, value);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        iterator.next();
        iterator.remove();
    }

    public void refresh(int key) {
        list.remove(Integer.valueOf(key));
        list.add(key);
    }
}
