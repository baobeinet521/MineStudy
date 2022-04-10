package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class reverseKGroup {
    /**
     * 题目说明：
     * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
     * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
     * 你不能更改节点中的值，只能更改节点本身。
     */
    public static void main(String[] args) {

    }

    /**
     * 非递归实现方式
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode pre = temp;
        ListNode end = temp;
        while (end.next != null){
            for (int i = 0; i < k && end != null;i++){
                end = end.next;
            }
            if (end == null)
                break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            ListNode reverseNode = reverseListNode(start);

            pre.next = reverseNode;
            start.next = next;

            pre = start;
            end = start;
        }
        return  temp.next;

    }

    public ListNode reverseListNode(ListNode node){
        ListNode cur = node;
        ListNode temp = null;
        ListNode tempHead = null;
        while (cur != null){
            tempHead = cur.next;
            cur.next = temp;
            temp = cur;
            cur = tempHead;
        }
        return temp;
    }

    /**
     * 递归实现方式
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return head;
        }

        ListNode tail = head;
        for (int i = 0; i < k; i++){
            if (tail == null)
                return head;
            tail = tail.next;
        }

        ListNode tempHead = reverseListNode(head, tail);
        head.next = reverseKGroup(tail, k);
        return tempHead;

    }

    public ListNode reverseListNode(ListNode start, ListNode end){
        ListNode  pre = null;
        ListNode next = null;
        while (start != end){
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        return pre;
    }
}
