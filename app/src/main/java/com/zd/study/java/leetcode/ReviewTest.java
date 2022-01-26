package com.zd.study.java.leetcode;

import com.zd.study.java.bean.ListNode;

import java.util.List;

public class ReviewTest {

    public static void testFindMissingNum() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                51, 52, 53, 55, 56, 57, 58, 59, 60,
                61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
                81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 94, 95, 96, 97, 98, 99, 100};

        int find = findMissingNumber(nums);
        System.out.println("findMissingNumber is = " + find);
    }

    public static void testLongestSubstring() {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "";
        String s5 = null;
        String s6 = "tmmzuxt";
        System.out.println(s1 + " longest length is : " + lengthOfLongestSubstring(s1));
        System.out.println(s2 + " longest length is : " + lengthOfLongestSubstring(s2));
        System.out.println(s3 + " longest length is : " + lengthOfLongestSubstring(s3));
        System.out.println(s4 + " longest length is : " + lengthOfLongestSubstring(s4));
        System.out.println(s5 + " longest length is : " + lengthOfLongestSubstring(s5));
        System.out.println(s6 + " longest length is : " + lengthOfLongestSubstring(s6));
    }

    public static void testReverse() {
        ListNode listNode4 = new ListNode(4, null);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        ListNode list = reverseList(listNode1);
        while (list != null) {
            System.out.print(list.val + "  ");
            list = list.next;
        }
    }

    public static void testMerge() {
        ListNode list1Node3 = new ListNode(4, null);
        ListNode list1Node2 = new ListNode(3, list1Node3);
        ListNode list1Node1 = new ListNode(1, list1Node2);

        ListNode list2Node3 = new ListNode(6, null);
        ListNode list2Node2 = new ListNode(5, list2Node3);
        ListNode list2Node1 = new ListNode(2, list2Node2);

        ListNode result = mergeList(list1Node1, list2Node1);
        while (result != null) {
            System.out.print(result.val + "  ");
            result = result.next;
        }
    }

    public static void testAddNumAsList() {
        ListNode list1Node3 = new ListNode(4, null);
        ListNode list1Node2 = new ListNode(3, list1Node3);
        ListNode list1Node1 = new ListNode(1, list1Node2);

        ListNode list2Node3 = new ListNode(6, null);
        ListNode list2Node2 = new ListNode(5, list2Node3);
        ListNode list2Node1 = new ListNode(2, list2Node2);
        ListNode twoResult = addTwoNum(list1Node1, list2Node1);
        while (twoResult != null) {
            System.out.print(twoResult.val + "  ");
            twoResult = twoResult.next;
        }
    }

    public static void testInorderTraversal() {
        TreeNode inOrder = new TreeNode(1);
        TreeNode leftOrder = new TreeNode(2);
        TreeNode rightOrder = new TreeNode(3);

        inOrder.left = leftOrder;
        inOrder.right = rightOrder;

        List<Integer> result = inorderTraversal(inOrder);

        if(result != null){
            for (int i = 0; i < result.size(); i++){
                System.out.print(result.get(i) + "  ");
            }
        }

    }

    public static void main(String[] args) {
        /*
        1、如何在一个 1 到 n 的整数数组中找到丢失的数字?
        (1)假设该数组是递增排序数组，如何实现
        (2)假设该数组是无序数组，如何实现
         */
        System.out.println();
        System.out.println("-----------------------第1题运行结果----------------------------");
        testFindMissingNum();

        /*
        2、给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
        打印答案应该是3， 1， 3， 0， 0， 5
         */
        System.out.println("-----------------------第2题运行结果----------------------------");
        testLongestSubstring();

        /*
        3、反转链表
        打印结果应该是 4， 3， 2， 1
         */
        System.out.println("-----------------------第3题运行结果----------------------------");
        testReverse();

        /*
        4、将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
        打印结果输出：1->2->3->4->5->6
         */
        System.out.println();
        System.out.println("-----------------------第4题运行结果----------------------------");
        testMerge();

        /*
        5、给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
        请你将两个数相加，并以相同形式返回一个表示和的链表。
        你可以假设除了数字 0 之外，这两个数都不会以 0开头。
        示例 1：
        输入：l1 = [1,3,4], l2 = [2,5,6]
        输出：[3,8,0,1]
        解释：431 + 652 = 1083.
         */
        System.out.println();
        System.out.println("-----------------------第5题运行结果----------------------------");
        testAddNumAsList();

        /**
         * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
         * 输入：root = [1,null,2,3]
         * 输出：[1,3,2]
         */

        System.out.println();
        System.out.println("-----------------------第6题二叉树遍历运行结果----------------------------");
        testInorderTraversal();
    }

    public static ListNode addTwoNum(ListNode list1Node1, ListNode list2Node1) {
        ListNode resNode = new ListNode(0);
        ListNode cur = resNode;
        int addData = 0;
        while (list1Node1 != null || list2Node1 != null || addData != 0){
            int data1 = list1Node1 != null ? list1Node1.val : 0;
            int data2 = list2Node1 != null ? list2Node1.val : 0;
            int sum  = data1 + data2 + addData;
            ListNode tempNode = new ListNode(sum % 10);
            addData = sum / 10;
            cur.next = tempNode;
            cur = cur.next;
            if(list1Node1 != null){
                list1Node1 = list1Node1.next;
            }
            if(list2Node1 != null){
                list2Node1 = list2Node1.next;
            }
        }
        return resNode.next;
    }

    public static int findMissingNumber(int[] nums) {
        if(nums == null){
            return -1;
        }

        int find = -1;
        int sum = (nums.length + 1) * (1 + nums.length + 1) >> 1;
        int tempSum = 0;
        for (int i = 0; i < nums.length; i++){
            tempSum  = tempSum + nums[i];
        }

        find = sum - tempSum;
        return find;
    }

    public static ListNode mergeList(ListNode list1Node1, ListNode list2Node1) {
        ListNode resNode = new ListNode(0);
        ListNode cur = resNode;
       while (list1Node1 != null && list2Node1 != null){
           if(list1Node1.val > list2Node1.val){
               cur.next = new ListNode(list2Node1.val);
               list2Node1 = list2Node1.next;
           }else {
               cur.next = new ListNode(list1Node1.val);
               list1Node1 = list1Node1.next;
           }
           cur = cur.next;
       }
       if(list1Node1 != null){
           cur.next = list1Node1;
       }
        if(list2Node1 != null){
            cur.next = list2Node1;
        }

        return resNode.next;
    }

    public static int lengthOfLongestSubstring(String s) {
        //todo-write your code
        return -1;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }


        ListNode resNode = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = resNode;
            resNode = head;
            head = temp;
        }

        return resNode;
    }

    private static int mCountNode = 0;
    public static List<Integer> inorderTraversal(TreeNode root) {
        if(root == null){
            return null;
        }



        return null;
    }

    public static int countNode(TreeNode root){
        if (root == null){
            return mCountNode;
        }

        return mCountNode;
    }
}
