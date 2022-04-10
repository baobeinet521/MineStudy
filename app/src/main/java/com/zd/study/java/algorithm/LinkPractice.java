package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

import java.util.HashSet;

public class LinkPractice {
    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(6);
        ListNode listNode3 = new ListNode(5,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);


        ListNode listNode33 = new ListNode(4,listNode3);
        ListNode listNode22 = new ListNode(3,listNode33);
        ListNode listNode11 = new ListNode(7,listNode22);

        ListNode result = FindFirstCommonNode(listNode1,listNode11);
        if(result != null){
            System.out.println(" 他们的第一个公共结点是：   " + result.val);
        }

    }
    /**
     * 判断链表是否有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slowNode = head.next;
        ListNode fastNode = head.next.next;
        while (fastNode != null && fastNode.next != null){
            if (slowNode == fastNode){
                return true;
            }else{
                slowNode = slowNode.next;
                fastNode = fastNode.next.next;
            }
        }
        return false;
    }

    /**
     * 输出链表入环第一个节点
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead == null || pHead.next == null){
            return null;
        }
        ListNode cur = pHead;
        HashSet<ListNode> set = new HashSet<>();
        while (cur != null){
            if(!set.contains(cur)){
                set.add(cur);
                cur = cur.next;
            } else {
                return cur;
            }
        }
        return null;
    }

    /**
     * 输出链表入环第一个节点
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop1(ListNode pHead) {
        if(pHead == null || pHead.next == null){
            return null;
        }
        ListNode slowNode = pHead.next;
        ListNode fastNode = pHead.next.next;
        while (fastNode != slowNode){
            if(fastNode == null || fastNode.next == null){
                return null;
            }
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        fastNode = pHead;
        while (fastNode != slowNode){
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }

    /**
     * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空
     * @param pHead1
     * @param pHead2
     * @return
     *
     * 优化代码
     */
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        ListNode pHead1Cur = pHead1;
        int count = 0;
        ListNode pHead2Cur = pHead2;
        while (pHead1Cur.next != null){
            count++;
            pHead1Cur = pHead1Cur.next;
        }
        while (pHead2Cur.next != null){
            count--;
            pHead2Cur = pHead2Cur.next;
        }
        if(pHead1Cur != pHead2Cur){
            return null;
        }
        ListNode cur1Node = count > 0 ? pHead1 : pHead2;
        ListNode cur2Node = cur1Node == pHead1 ? pHead2 : pHead1;
        count = Math.abs(count);
        while (count > 0){
            count--;
            cur1Node = cur1Node.next;

        }
        while (cur1Node != cur2Node){
            cur1Node = cur1Node.next;
            cur2Node = cur2Node.next;

        }
        return cur1Node;
    }
}
