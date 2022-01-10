package com.zd.study.java.leetcode;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ReverseList {

    /**
     * 描述
     * 给定一个单链表的头结点pHead，长度为n，反转该链表后，返回新链表的表头。
     *
     * 数据范围： n\leq1000n≤1000
     * 要求：空间复杂度 O(1)O(1) ，时间复杂度 O(n)O(n) 。
     *
     * 如当输入链表{1,2,3}时，
     * 经反转后，原链表变为{3,2,1}，所以对应的输出为{3,2,1}。
     *
     */
    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(4,listNode3);
        ListNode listNode1 = new ListNode(2,listNode2);

        ListNode result = ReverseListStack(listNode1);
        ListNode temp = result;
        while (temp != null){
            System.out.println("    " + temp.val);
            temp = temp.next;
        }
    }

    /**
     * 使用一个列表存储节点数据
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode temp = head;
        ListNode result = new ListNode(0);
        ListNode cur = result;

        Vector<ListNode> listVec = new Vector<>();

        while (temp != null){
            ListNode nodeTemp = new ListNode(temp.val);
            listVec.add(nodeTemp);
            temp = temp.next;
        }

        for(int i = listVec.size() - 1; i >= 0; i--){
            cur.next = listVec.get(i);
            cur = cur.next;
        }
        return result.next;

    }

    /**
     * 利用栈 来解决
     * @param head
     * @return
     */
    public static ListNode ReverseListStack(ListNode head){
        if (head == null){
            return head;
        }
        ListNode headTemp = head;

        ListNode result = new ListNode(0);
        ListNode temp = result;

        Stack<ListNode> stack = new Stack<>();
        while (headTemp != null){
            ListNode tempData = new ListNode(headTemp.val);
            stack.push(tempData);
            headTemp = headTemp.next;
        }

        while (!stack.isEmpty()){
            temp.next = stack.pop();
            temp = temp.next;
        }

        return result.next;

    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode resNode = null;
        while(head != null){
            ListNode tempNode = head.next;
            head.next = resNode;
            resNode = head;
            head = tempNode;
        }
        return resNode;

    }
}
