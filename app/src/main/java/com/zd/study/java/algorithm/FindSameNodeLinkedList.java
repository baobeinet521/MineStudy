package com.zd.study.java.algorithm;

import com.zd.study.java.bean.ListNode;

public class FindSameNodeLinkedList {
    /**
     * 求两个链表的第一个相交节点（链表可能有环，可能无环）
     * @param args
     */
    public static void main(String[] args) {

    }

    public ListNode getNode(ListNode node1, ListNode node2){
        //两个链表都有环
        if(hasLoop(node1) && hasLoop(node2)){
            ListNode node1Loop = getLoopNode(node1);
            ListNode node2Loop = getLoopNode(node2);
            return getLoopNode(node1,node1Loop,node2,node2Loop);
        }
        //两个链表都无环
        if(!hasLoop(node1) && !hasLoop(node2)){
            return getNoLoopNode(node1, node2);
        }
        //一个链表有环，一个链表无环肯定不相交
        return null;
    }

    public boolean hasLoop(ListNode node){
        if(node == null || node.next == null){
            return false;
        }
        ListNode slow = node.next;
        ListNode faster = node.next.next;
        while (faster != null && faster.next != null){
            if(slow == faster){
                return true;
            } else {
                slow = slow.next;
                faster = faster.next.next;
            }
        }
        return false;
    }

    public ListNode getLoopNode(ListNode node){
        if(node == null || node.next == null){
            return null;
        }
        ListNode slow = node.next;
        ListNode faster = node.next.next;
        while (faster != null && faster.next != null){
            if(slow == faster){
                faster = node;
                break;
            }else {
                slow = slow.next;
                faster = faster.next.next;
            }
        }
        while (slow != faster){
            slow = slow.next;
            faster = faster.next;
        }
        return slow;
    }

    public ListNode getLoopNode(ListNode node1, ListNode node1Loop,ListNode node2, ListNode node2Loop){
        ListNode cur1 = null;
        ListNode cur2 = null;
        //入环节点是同一个
        if (node1Loop == node2Loop) {
            cur1 = node1;
            cur2 = node2;
            int count = 0;
            while (cur1 != node1Loop){
                cur1 = cur1.next;
                count++;
            }
            while (cur2 != node2Loop){
                cur2 = cur2.next;
                count--;
            }
            cur1 = count > 0 ? node1 : node2;
            cur2 = count > 0 ? node2 : node1;
            count = Math.abs(count);
            while (count > 0){
                count--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //入环节点不是同一个
            cur1 = node1.next;
            while (cur1 != node1Loop){
                if(cur1 == node2Loop){
                    return cur1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
    //求无环链表相交节点
    public ListNode getNoLoopNode(ListNode node1, ListNode node2){
        if(node1 == null || node2 == null){
            return null;
        }
        ListNode cur1 = node1;
        ListNode cur1End = null;
        int node1Count = 0;
        while (cur1 != null){
            node1Count++;
            if(cur1.next == null){
                cur1End = cur1;
            }
            cur1 = cur1.next;
        }

        ListNode cur2 = node2;
        ListNode cur2End = null;
        int node2Count = 0;
        while (cur2 != null){
            node2Count++;
            if(cur2.next == null){
                cur2End = cur2;
            }
            cur2 = cur2.next;
        }
        if(cur1End != cur2End){
            return null;
        }

         cur1 = node1Count > node2Count ? node1 : node2;
         cur2 = node1Count < node2Count ? node1 : node2;

        int difCount = Math.abs(node1Count - node2Count);
        int jumpCount = 0;
        while (jumpCount < difCount){
            cur1 = cur1.next;
            jumpCount++;
        }

        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

}


