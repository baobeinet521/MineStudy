package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class MergeOrderList {
    public static void main(String[] args){


    }

    /**
     * 合并两个有序链表
     * 已解答
     * 简单
     * 相关标签
     * premium lock icon
     * 相关企业
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     *
     *
     * 示例 1：输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 示例 2：
     *
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     *
     *
     * 提示：
     *
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     * @param list1
     * @param list2
     * @return
     */

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        if (list1 == null || list2 == null) {
            head = list1 == null ? list2 : list1;
            return head;
        }
        ListNode h1 = list1;
        ListNode h2 = list2;
        ListNode cur = null;
        if (h1.val < h2.val) {
            head = h1;
            h1 = h1.next;
        } else {
            head = h2;
            h2 = h2.next;
        }
        cur = head;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                cur.next = h1;
                cur = cur.next;
                h1 = h1.next;
            } else {
                cur.next = h2;
                cur = cur.next;
                h2 = h2.next;
            }
        }
        if (h1 == null) {
            cur.next = h2;
        }
        if (h2 == null) {
            cur.next = h1;
        }
        return head;
    }










    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
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
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }else if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }


}
