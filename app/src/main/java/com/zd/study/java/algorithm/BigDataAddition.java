package com.zd.study.java.algorithm;

public class BigDataAddition {
    public static void main(String[] args) {
        String s =  "";
        String t = "114514";
        String result = solve(s, t);
        System.out.println("   " + result);
    }
    public static String solve (String s, String t) {
        if(s.isEmpty()){
            return t;
        }
        if(t.isEmpty()){
            return s;
        }

        int sLength = s.length();
        int tLength = t.length();
        int count = Math.abs(sLength - tLength);
        boolean flag = sLength >= tLength ? true : false;
        StringBuilder tagStr = new StringBuilder();
        for (int i = 0; i < count; i++){
            tagStr.append("0");
        }
        String otherStr = "";
        if (flag){
            tagStr.append(t);
            otherStr = s;
        }else {
            tagStr.append(s);
            otherStr = t;
        }

        int[] result = new int[tagStr.length()];
        int tenData = 0;
        for (int i = tagStr.length() - 1; i >= 0; i--){
            int data =  Character.getNumericValue(otherStr.charAt(i)) + Character.getNumericValue(tagStr.charAt(i)) + tenData;
            tenData = data / 10;
            result[i] = (data % 10);
        }
        StringBuilder str = new StringBuilder();
        if (tenData > 0){
            str.append(tenData);
        }
        for (int i = 0; i < result.length; i++){
            str.append(result[i]);
        }
        return str.toString();
    }
}
