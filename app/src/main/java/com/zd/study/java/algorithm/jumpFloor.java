package com.zd.study.java.algorithm;

public class jumpFloor {
    public static void main(String[] args) {

    }
    //递归
    public int jumpFloor1(int target) {
        if(target <= 1){
            return 1;
        }
        return jumpFloor1(target - 1) + jumpFloor1(target - 2);

    }

    //优化递归，递归存在很多重复计算
    public int jumpFloor2(int target) {
        if(target <= 1){
            return 1;
        }
        int[] f = new int[target];
        if (f[target] > 0) return f[target];
        return f[target] = jumpFloor2(target - 1) + jumpFloor2(target - 2);
    }

    //继续优化，动态规划
    public int jumpFloor3(int target) {
        int a = 1;
        int b = 1;
        int c = 1;
        for (int  i = 2; i <= target; i ++){
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
