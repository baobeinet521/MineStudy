package com;

import com.zd.study.java.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Test {
    public List<List<Integer>> test(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> nodeList = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        int level = 1;
        TreeNode nextEnd = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            if(level % 2 == 1){
                TreeNode node= stack1.pop();
                nodeList.add(node.val);
                if(node.right != null){
                    stack2.add(node.right);
                }
                if(node.left != null){
                    stack2.add(node.left);
                }
                if (stack1.isEmpty()){
                    result.add(nodeList);
                    level++;
                    nodeList = new ArrayList<>();
                }
            }else {
                TreeNode node= stack2.pop();
                nodeList.add(node.val);
                if(node.left != null){
                    stack1.add(node.left);
                }
                if(node.right != null){
                    stack1.add(node.right);
                }

                if (stack2.isEmpty()){
                    result.add(nodeList);
                    level++;
                    nodeList = new ArrayList<>();
                }
            }
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int level = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (level % 2 == 1) {
                if (node.left != null) {
                    queue.add(node.left);
                    nextEnd = node.left;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    nextEnd = node.right;
                }
            } else {
                if (node.right != null) {
                    queue.add(node.right);
                    nextEnd = node.right;
                }
                if (node.left != null) {
                    queue.add(node.left);
                    nextEnd = node.left;
                }
            }
            list.add(node.val);
            if (curEnd == node) {
                curEnd = nextEnd;
                level++;
                lists.add(list);
                list = new ArrayList<>();
            }
        }




        Stack<TreeNode> stack1 = new Stack();
        Stack<TreeNode> stack2 = new Stack();

        stack1.add(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (level % 2 == 1) {
                TreeNode node = stack1.pop();
                list.add(node.val);
                if (node.right != null) {
                    stack2.add(node.right);
                }
                if (node.left != null) {
                    stack2.add(node.left);
                }
                if (stack1.isEmpty()) {
                    level++;
                    lists.add(list);
                    list = new ArrayList<>();
                }
            } else {
                TreeNode node = stack2.pop();
                list.add(node.val);
                if (node.right != null) {
                    stack1.add(node.right);
                }
                if (node.left != null) {
                    stack1.add(node.left);
                }
                if (stack2.isEmpty()) {
                    level++;
                    lists.add(list);
                    list = new ArrayList<>();
                }
            }
        }


        return lists;
    }


    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();

        if (root == null)
            return lists;

        List<Integer> list = new ArrayList<>();
        int level = 1;
        Stack<TreeNode> stack1 = new Stack();
        Stack<TreeNode> stack2 = new Stack();
        stack1.add(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (level % 2 != 1) {
                TreeNode node = stack1.pop();
                list.add(node.val);
                if (node.left != null) {
                    stack2.add(node.left);
                }
                if (node.right != null) {
                    stack2.add(node.right);
                }
                if (stack1.isEmpty()) {
                    level++;
                    lists.add(list);
                    list = new ArrayList<>();
                }
            } else {
                TreeNode node = stack2.pop();
                list.add(node.val);
                if (node.right != null) {
                    stack1.add(node.right);
                }
                if (node.left != null) {
                    stack1.add(node.left);
                }
                if (stack2.isEmpty()) {
                    level++;
                    lists.add(list);
                    list = new ArrayList<>();
                }
            }
        }
        return lists;
    }


}
