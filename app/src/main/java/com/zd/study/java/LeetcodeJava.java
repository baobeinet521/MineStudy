package com.zd.study.java;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LeetcodeJava {
    public static void main(String[] args){

        isValid("()[]{}");
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                result[0] = map.get(nums[i]);
                result[1] = i;
                break;
            }
            map.put(target - nums[i],i);
        }
        return result;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
