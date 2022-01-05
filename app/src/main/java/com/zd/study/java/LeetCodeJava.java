package com.zd.study.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LeetCodeJava {
    public static void main(String[] args){

//        isValid("()[]{}");
        testAddTwoNumbers();
    }

    public static void testAddTwoNumbers(){
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(4,listNode3);
        ListNode listNode1 = new ListNode(2,listNode2);


        ListNode listNode7 = new ListNode(4);
        ListNode listNode6 = new ListNode(6,listNode7);
        ListNode listNode5 = new ListNode(5,listNode6);

//        ListNode listNode7 = new ListNode(9);
//        ListNode listNode6 = new ListNode(9,listNode7);
//        ListNode listNode5 = new ListNode(9,listNode6);
//        ListNode listNode4 = new ListNode(9,listNode5);
//        ListNode listNode3 = new ListNode(9,listNode4);
//        ListNode listNode2 = new ListNode(9,listNode3);
//        ListNode listNode1 = new ListNode(9,listNode2);
//
//
//        ListNode listNode11 = new ListNode(9);
//        ListNode listNode10 = new ListNode(9,listNode11);
//        ListNode listNode9 = new ListNode(9,listNode10);
//        ListNode listNode8 = new ListNode(9,listNode9);

        ListNode result = addTwoNumbers(listNode1, listNode5);
//        System.out.println("    " + result.val);
        ListNode temp = result;
        while (temp != null){
            System.out.println("    " + temp.val);
            temp = temp.next;
        }
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


    /* Definition for singly-linked list.*/
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null){
            if(l1.val > l2.val){
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            } else {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }

        }
        if(l1 == null){
            cur.next = l2;
        }
        if(l2 == null){
            cur.next = l1;
        }
        return dummyHead.next;
    }

    /**
     * 递归方式
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }else if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int result = nums[0];
        for(int i = 0;i < nums.length; i++){
            if(sum > 0){
                sum = sum + nums[i];
            }else {
                sum = nums[i];
            }
            result = Math.max(sum,result);
        }
        return result;
    }

    /**
     * 力扣上提交会超时
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode cur = result;
        int add = 0;
        int num = 0;
        while(l1 != null && l2!= null){
            int sum = l1.val + l2.val + add;
            if(sum >= 10){
                num = sum % 10;
                add = sum / 10;
            }else{
                num = sum;
            }
            cur.next = new ListNode(num);
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }

        while (l1 != null){
            if(add != 0){
                int temp = l1.val + add;
                cur.next = new ListNode(temp);
                l1 = l1.next;
                add = 0;
            } else {
                cur.next = l1;
            }
            cur = cur.next;

        }

        while (l2 != null){
            if(add != 0){
                int temp = l2.val + add;
                cur.next = new ListNode(temp);
                l2 = l2.next;
                add = 0;
            } else {
                cur.next = l2;
            }
            cur = cur.next;

        }
        return result.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode cur = result;
        int add = 0;
        while(l1 != null || l2 != null || add != 0){
            int vall1 = l1 != null ? l1.val : 0;
            int vall2 = l2 != null ? l2.val : 0;
            int sum = vall1 + vall2 + add;

            add = sum / 10;

            cur.next = new ListNode(sum % 10);
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }

            cur = cur.next;
        }

        return result.next;
    }
}
