package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class RemoveNthFromEnd {
    public static void main(String[] args) {
//        ListNode node2 = new ListNode(2);
//        ListNode node1 = new ListNode(1,node2);
//        removeNthFromEnd(node1, 1);
    }
    public static ListNode removeNthFromEnd (ListNode head, int n) {
        if(head == null){
            return null;
        }
        if(n == 0){
            return head;
        }

        ListNode headTemp = head;
        int length = 0;
        int nodeIndex = 1;
        while (headTemp != null){
            length++;
            headTemp = headTemp.next;
        }

        headTemp = head;
        ListNode result = headTemp;
        if (n == length) {
            result = headTemp.next;
            return result;
        }
        while (headTemp != null){
            if(nodeIndex == length - n){
                if(headTemp.next.next != null){
                    headTemp.next = headTemp.next.next;
                }else{
                    headTemp.next = null;
                }
                break;
            }else {
                headTemp = headTemp.next;
                nodeIndex++;
            }

        }
        return result;
    }

    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        if(head == null){
            return null;
        }
        if(n == 0){
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        int length = 0;
        int step = 0;
        while (fast != null){
            fast = fast.next;
            length++;
        }
        fast = head;
        if (n == length) {
            head = fast.next;
            return head;
        }
        while (fast != null){
            if(step <= n){
                fast = fast.next;
                step++;
            }else {
                fast = fast.next;
                slow = slow.next;
            }
        }
        slow.next = slow.next.next;
        return head;
    }


}
