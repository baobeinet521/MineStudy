package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class AddInList {
    /**
     * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
     * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
     * 数据范围：0 \le n,m \le 10000000≤n,m≤1000000，链表任意值 0 \le val \le 90≤val≤9
     * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
     *
     * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
     */

    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(7,null);
        ListNode listNode2 = new ListNode(3,listNode3);
        ListNode listNode1 = new ListNode(9,listNode2);


        ListNode listNode22 = new ListNode(3,null);
        ListNode listNode11 = new ListNode(6,listNode22);
        ListNode temp = addInList(listNode1, listNode11);
        System.out.println("     ");

    }

    public static ListNode addInList (ListNode head1, ListNode head2) {
        if(head1 == null){
            return  head2;
        }
        if(head2 == null){
            return head1;
        }
        ListNode head1New = reversalListNode(head1);
        ListNode head2New = reversalListNode(head2);
        ListNode temp = new ListNode();
        ListNode result = temp;
        int tenNum = 0;
        int sum = 0;
        while (head1New != null || head2New != null){
            ListNode resultTemp = new ListNode();
            if(head1New != null && head2New != null){
                sum = head1New.val + head2New.val + tenNum;
            }else if(head1New != null && head2New == null){
                sum = head1New.val + tenNum;
            }else if(head1New == null && head2New != null){
                sum = head2New.val + tenNum;
            }
            tenNum = sum / 10;
            int singleDigit = sum % 10;
            resultTemp.val = singleDigit;
            temp.next = resultTemp;
            temp = temp.next;
            if (head1New != null) {
                head1New = head1New.next;
            }
            if (head2New != null) {
                head2New = head2New.next;
            }
        }
        if(tenNum > 0){
            ListNode nodeLast = new ListNode();
            nodeLast.val = tenNum;
            temp.next = nodeLast;
        }

        return reversalListNode(result.next);
    }

    public static ListNode reversalListNode(ListNode head){
        if(head == null){
            return null;
        }
        ListNode temp = head;
        ListNode newHead = null;
        while (temp != null){
            temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
