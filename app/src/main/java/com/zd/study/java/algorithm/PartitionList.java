package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class PartitionList {
    public static void main(String[] args) {

    }

    /**
     * 分隔链表
     * 中等
     * 相关标签
     * premium lock icon
     * 相关企业
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * <p>
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode next = null;
        ListNode bigHead = null;
        ListNode bigTail = null;
        ListNode smallHead = null;
        ListNode smallTail = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < x) {
                if (smallHead == null){
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = smallTail.next;
                }
            }
            if (head.val >= x) {
                if (bigHead == null){
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = bigTail.next;
                }
            }
            head = next;
        }
        if (smallTail != null){
            smallTail.next = bigHead;
        }else {
            smallHead = bigHead;
        }
        return smallHead;
    }
}
