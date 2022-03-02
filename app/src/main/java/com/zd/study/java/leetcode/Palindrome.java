package com.zd.study.java.leetcode;

import java.util.Stack;

/**
 * 回文
 */
public class Palindrome {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {
        boolean tag = true;
        String xStr = String.valueOf(x);
        Stack<Character> characterStack = new Stack<>();
        for(int i = 0; i < xStr.length(); i++){
            characterStack.push(xStr.charAt(i));
        }
        for (int i = 0; i < xStr.length(); i ++){
            char data = characterStack.pop().charValue();
            if(data != xStr.charAt(i)){
                tag = false;
                break;
            }
        }
        return tag;
    }

    public boolean isPalindromeTwo(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)){
            return false;

        }
        int revertNum = 0;
        while (x > revertNum){
            revertNum = revertNum * 10 + x % 10;
            x /= 10;
        }
        return revertNum == x || revertNum / 10 == x;
    }
}
