package com.zd.study.java.practice;

import com.zd.study.java.bean.ListNode;
import com.zd.study.java.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {

        testReverseList();
//        int[] test = new int[]{4,7,3,5};
//        int[] res = sortArrayByHeap(test);
//        System.out.println(Arrays.toString(res));
    }

    /**
     *  反转链表测试
     */
    public static void testReverseList(){
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(4,listNode3);
        ListNode listNode1 = new ListNode(2,listNode2);

        ListNode result = reverseList(listNode1);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode temp = null;
        ListNode cur = null;
        while (head != null){
            cur = head.next;
            head.next = temp;
            temp = head;
            head = cur;
        }
        return temp;
    }


    public int[] sortArrayBubble(int[] nums) {
        if(nums == null || nums.length == 1){
            return nums;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length - i - 1;j++){
                if(nums[i] > nums[j]){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }

            }
        }
        return nums;

    }

    public int[] sortArraySelect(int[] nums) {
        if(nums == null || nums.length == 1){
            return nums;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i  + 1; j < nums.length;j++){
                if(nums[i] > nums[j]){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }

            }
        }
        return nums;

    }

    /**
     * 插入
     * @param nums
     * @return
     */
    public int[] sortArrayInsert(int[] nums) {
        if(nums == null || nums.length == 1){
            return nums;
        }
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i;j++){
                if(nums[i] < nums[j]){
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }

            }
        }
        return nums;

    }

    public static int[] sortArrayByHeap(int[] nums) {

        Comparator comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };

        PriorityQueue queue = new PriorityQueue(100, comparator);
        for (int i : nums) {
            queue.add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)queue.poll();
        }
        return nums;
    }


    /**
     *归并
     * @param nums
     * @return
     */
    public static int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 1){
            return nums;
        }

        return sortData(nums, 0 , nums.length - 1);

    }

    public static int[] sortData(int[] nums,int start,int end){
        if(start == end){
            return new int[]{nums[start]};
        }
        int mid = start + (end - start) / 2;
        int[] left = sortData(nums, start, mid);
        int[] right = sortData(nums, mid + 1, end);
        return mergeSort(left, right);
    }

    public static int[] mergeSort(int[] numLeft, int[] numRight){
        int[] res = new int[numLeft.length + numRight.length];
        int indexRes = 0;
        int indexL = 0;
        int indexR = 0;
        while (indexL < numLeft.length && indexR < numRight.length){
            if(numLeft[indexL] <= numRight[indexR]){
                res[indexRes++] = numLeft[indexL++];
            }else{
                res[indexRes++] = numRight[indexR++];
            }
        }

        while (indexR < numRight.length){
            res[indexRes++] = numRight[indexR++];
        }
        while (indexL < numLeft.length){
            res[indexRes++] = numLeft[indexL++];
        }
        return res;

    }

    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int[] index = new int[128];
        for (int i = 0; i < index.length; i++){
            index[i] = -1;
        }

        int result = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++){
            start = Math.max(start, index[s.charAt(i)] + 1);
            result = Math.max(result, i - start + 1);
            index[s.charAt(i)] = i;

        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<Integer> rightSeeList = new ArrayList<>();

        List<List<Integer>> lists = new ArrayList<>();
        if (root == null)
            return lists;
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int curLevel = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }

            list.add(node.val);

            if (curEnd == node) {
                rightSeeList.add(node.val);
                lists.add(curLevel++, list);
                list = new ArrayList<>();
                curEnd = nextEnd;
            }
        }
        return null;
    }

    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        if (k > nums.length){
            return 0;
        }

        int[] result = sortData(nums, 0, nums.length - 1);

        return result[nums.length - k];

    }


    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(input == null || input.length == 0){
            return result;
        }
        if(k >= input.length){
            for(int i = 0; i < input.length; i++ ){
                result.add(input[i]);
            }
            return result;
        }

        int[] sortResult = sortData(input, 0, input.length - 1);

        for(int i = 0; i < k; i++ ){
            result.add(sortResult[i]);
        }
        return result;

    }

//    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
//        // write code here
//    }

    List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return result;
        }
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return result;
    }


}
