package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class MergeNodeList {
    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(5);
        ListNode listNode2 = new ListNode(3,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);

        ListNode listNode33 = new ListNode(6);
        ListNode listNode22 = new ListNode(4,listNode33);
        ListNode listNode11 = new ListNode(2,listNode22);

        ListNode result = Merge2(listNode1,listNode11);
        ListNode temp = result;
        while (temp != null){
            System.out.println("    " + temp.val);
            temp = temp.next;
        }

    }
    public static ListNode Merge1(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode result = new ListNode(0);
        ListNode cur = result;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;

        }
        while (list1 != null){
            cur.next = list1;
            list1 = list1.next;
            cur = cur.next;
        }
        while (list2 != null){
            cur.next = list2;
            list2 = list2.next;
            cur = cur.next;
        }
        return result.next;
    }

    public static ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }else{
            if(list1.val < list2.val){
                list1.next = Merge2(list1.next, list2);
                return list1;

            }else {
                list2.next = Merge2(list1,list2.next);
                return list2;
            }
        }
    }
}
