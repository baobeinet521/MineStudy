package com.zd.study.huaweitest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HuaweiTest {
    public static void main(String[] args) {

    }

    public static void test1() {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] array = new int[length];
        int index = 0;
        while (in.hasNextInt()) {
            int a = in.nextInt();
            array[index++] = a;
        }

        boolean has = false;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                for (int k = 0; k < array.length;k++){
                    int A = array[i];
                    int B = array[j];
                    int C = array[k];
                    if(A == B + 2 * C && A != B && A != C && B != C){
                        has = true;
                        System.out.println(A + " " + B + " " + C);
                    }
                }
            }
        }
        if(!has){
            System.out.println(0);
        }

    }

    public static void test2() {
        Scanner in = new Scanner(System.in);
        String str1 = "";
        String str2 = "";
        while (in.hasNext()) {
            str1 = in.nextLine();
            str2 = in.nextLine();
        }
        if (str1.length() == 0 || str2.length() == 0) {
            System.out.println(-1);
        } else {
            int preIndex = -1;
            int indexNow = -1;
            for (int i = 0; i < str1.length(); i++) {
                if (str2.contains(str1.charAt(i) + "")) {
                    indexNow = str2.indexOf(str1.charAt(i));
                    if (indexNow <= preIndex) {
                        System.out.println(-1);
                    } else {
                        preIndex = indexNow;
                        if (i == str1.length() - 1) {
                            System.out.println(indexNow);
                        }
                    }
                } else {
                    System.out.println(-1);
                    break;
                }
            }

        }

    }

    public static void test3() {
        Scanner in = new Scanner(System.in);
        String data = in.nextLine();
        int waitTime = in.nextInt();
        String[] task = data.split(",");
        int taskTime = task.length;
        List<Integer> taskList = new ArrayList<>();
        List<Integer> taskListEx = new ArrayList<>();
        for (int i = 0;i < task.length; i++){
            taskList.add(Integer.parseInt(task[i]));
            taskListEx.add(Integer.parseInt(task[i]));
        }
        int findData = 0;
        for (int k = 1; k < taskList.size(); k++) {
            int taskExData = taskList.get(k);
            boolean find = false;
            for (int i = k; i < taskListEx.size(); i++) {
                if (taskListEx.get(k) != taskExData && taskListEx.get(k)!= Integer.MIN_VALUE) {
                    find = true;
                    findData = taskListEx.get(k);
                    break;
                }
            }
            if (!find){
                taskTime += waitTime;
                taskListEx.set(k, Integer.MIN_VALUE);
            }else {
                taskListEx.remove(findData);
            }

        }
        System.out.println(taskTime);
    }
}
