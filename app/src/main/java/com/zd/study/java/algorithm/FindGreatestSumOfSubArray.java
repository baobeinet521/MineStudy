package com.zd.study.java.algorithm;

public class FindGreatestSumOfSubArray {

    public int FindGreatestSumOfSubArrayTest(int[] array) {
        int max = 0;
        if(array == null || array.length == 0){
            return max;
        }
        int[] dp = new int[array.length];
        dp[0] = array[0];
        max = array[0];
        for (int i = 0; i < array.length; i++){
            dp[i] = Math.max(array[i] + dp[i - 1], array[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int FindGreatestSumOfSubArrayTest1(int[] array) {
        int max = 0;
        if(array == null || array.length == 0){
            return max;
        }
        int sum = array[0];
        max = array[0];
        for (int i = 1; i < array.length; i++){
            sum = Math.max(array[i] + sum, array[i]);
            max = Math.max(sum, max);
        }
        return max;
    }
}

