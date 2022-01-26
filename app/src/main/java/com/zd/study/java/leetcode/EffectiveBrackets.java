package com.zd.study.java.leetcode;

import java.util.Stack;

public class EffectiveBrackets {
    public static void main(String[] args) {

    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * 示例 2：
     *
     * 输入：s = "()[]{}"
     * 输出：true
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }

        Stack<Character> charStack = new Stack<>();

        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                charStack.push(s.charAt(i));
            }
            if(s.charAt(i) == ')'){
                if(!charStack.isEmpty() && charStack.peek() == '('){
                    charStack.pop();
                }else{
                    return false;
                }
            }
            if(s.charAt(i) == ']'){
                if(!charStack.isEmpty() && charStack.peek() == '['){
                    charStack.pop();
                }else{
                    return false;
                }
            }
            if(s.charAt(i) == '}'){
                if(!charStack.isEmpty() && charStack.peek() == '{'){
                    charStack.pop();
                }else{
                    return false;
                }
            }
        }
        return charStack.empty();

    }
}
