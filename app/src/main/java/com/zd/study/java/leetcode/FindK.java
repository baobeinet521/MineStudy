package com.zd.study.java.leetcode;

public class FindK {
    public static void main(String[] args) {
//        int[] a = new int[]{1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663};
//        int n = 49;
//        int k = 24;

        int[] a = new int[]{10,10,9,9,8,7,5,6,4,3,4,2};
        int n = 12;
        int k = 3;
        int result = findKth(a,n ,k);
        System.out.println(" FindK    " + result);
    }
    public static int findKth(int[] a, int n, int K) {
        // write code here
//        int[] temp = dataSort(a, 0, n - 1);
//        for (int i = 0; i < temp.length; i++){
//            System.out.print(temp[i] + "   ");
//        }
//
//        int index = n - K;
//        return temp[index];

        sortDataQuick(a, 0, n - 1);
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i] + "   ");
        }

        int index = n - K;
        return a[index];
    }

    public static void sortDataQuick(int[] nums, int left, int right){
        if(left == right){
            return;
        }
        int random = (int)Math.random()*(left + 1 - right) + right;
        swap(nums,right,random);
        int flag = nums[right];
        int bigIndex = right;
        int lessIndex = left - 1;
        int index = left;
        while (index < bigIndex){
            if(nums[index] < flag){
                swap(nums, index, lessIndex + 1);
                lessIndex++;
                index++;
            }else if(nums[index] > flag){
                swap(nums, index, bigIndex - 1);
                bigIndex--;
            }else{
                index++;
            }
        }
        swap(nums, right, bigIndex);
        if (lessIndex > left){
            sortDataQuick(nums, left, lessIndex);
        }
        if (bigIndex < right){
            sortDataQuick(nums, bigIndex + 1, right);
        }
    }

    public static int[] swap(int[] data, int indexOne, int indexTwo){
        int temp = data[indexOne];
        data[indexOne] = data[indexTwo];
        data[indexTwo] = temp;
        return data;
    }





    public static int[] dataSort(int[] a ,int right, int left){
        if (right == left) {
            return new int[]{a[right]};
        }
        int mid = right + (left - right) / 2;
        int[] leftA =  dataSort(a, right, mid);
        int[] rightA = dataSort(a, mid + 1, left);
        return mergeData(leftA,rightA);
    }

    public static int[] mergeData(int[] left, int[] right){
        int lIndex = 0;
        int rIndex = 0;
        int curIndex = 0;
        int[] result = new int[left.length + right.length];
        while (lIndex < left.length && rIndex < right.length){
            if(left[lIndex] < right[rIndex]){
                result[curIndex++] = left[lIndex++];
            } else {
                result[curIndex++] = right[rIndex++];
            }
        }
        while (lIndex < left.length){
            result[curIndex++] = left[lIndex++];
        }
        while (rIndex < right.length){
            result[curIndex++] = right[rIndex++];
        }
        return result;
    }
}
