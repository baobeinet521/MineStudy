package com.zd.study.java.leetcode;

import com.zd.study.java.bean.ListNode;

public class AddTwoNumber {
    public static void main(String[] args) {
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

    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * @param l1
     * @param l2
     * @return
     */
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
