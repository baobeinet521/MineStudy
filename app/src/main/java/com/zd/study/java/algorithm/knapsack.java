package com.zd.study.java.algorithm;

public class knapsack {
    /**
     * 描述
     * 已知一个背包最多能容纳体积之和为v的物品
     *
     * 现有 n 个物品，第 i 个物品的体积为 vi , 重量为 wi
     *
     * 求当前背包最多能装多大重量的物品?
     */

    /**
     * 用递归方式解决
     * @param V
     * @param n
     * @param vw
     * @return
     */
    public int knapsack (int V, int n, int[][] vw) {
        if(V == 0 || vw == null || vw.length == 0){
            return -1;
        }

       return process(V, vw, n, 0);
    }

    public int process(int V, int[][]vw, int n,int index){
        if(V < 0){
            return -1;
        }
        if (index == n){
            return 0;
        }
        int p1 = process(V, vw, n, index + 1);
        int p2 = 0;
        int next = process(V - vw[index][0], vw, n, index + 1);
        if(next != -1){
            p2 = vw[index][1] + next;
        }
        return Math.max(p1, p2);
    }


    //动态规划
    public int knapsack1(int V, int n, int[][] vw) {
        if(V == 0 || vw == null || vw.length == 0){
            return -1;
        }
        int[][] dp =  new int[n + 1][V + 1];
        for (int index = n - 1; index >= 0; index--){
            for (int rest = 0; rest <= V; rest++){
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - vw[index][0] < 0 ? -1 : dp[index + 1][rest - vw[index][0]];
                if(next != -1){
                    p2 = next + vw[index][1];
                }
                dp[index][rest] = Math.max(p1, p2);
            }

        }
        return dp[0][V];
    }
}
