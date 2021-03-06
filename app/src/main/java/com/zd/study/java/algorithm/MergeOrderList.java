package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class MergeOrderList {
    public static void main(String[] args){


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


}
