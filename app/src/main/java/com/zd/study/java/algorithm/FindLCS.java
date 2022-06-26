package com.zd.study.java.algorithm;

public class FindLCS {
    /**
     * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
     * 题目保证str1和str2的最长公共子串存在且唯一。
     *
     * 数据范围： 1 \le |str1|,|str2| \le 50001≤∣str1∣,∣str2∣≤5000
     * 要求： 空间复杂度 O(n^2)O(n
     * 2
     *  )，时间复杂度 O(n^2)O(n
     * 2
     *  )
     * @param str1
     * @param str2
     * @return
     */
    public String LCS (String str1, String str2) {
        int max = 0;
        int pos = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for(int i = 1; i <= str1.length(); i++){
            for (int j = 1; j <= str2.length(); j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j] = dp[i -1][j - 1] + 1;
                }else {
                    dp[i][j] = 0;
                }
                if(dp[i][j] > max){
                    max = dp[i][j];
                    pos = i - 1;
                }
            }
        }

        return str1.substring(pos - max + 1, pos + 1);
    }
}
