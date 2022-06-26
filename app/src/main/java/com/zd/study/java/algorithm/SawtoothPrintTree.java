package com.zd.study.java.algorithm;

import java.util.ArrayList;
import java.util.Stack;

public class SawtoothPrintTree {
    public static void main(String[] args) {

    }
    //之字形打印二叉树
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> nodeList = new ArrayList<>();
        if(pRoot == null){
            return result;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        int level = 1;
        stack1.add(pRoot);
        TreeNode node = null;
        while ((!stack1.isEmpty() || !stack2.isEmpty()) && level > 0){
            if(level % 2 != 0){
                if(!stack1.isEmpty()){
                    node = stack1.pop();
                    nodeList.add(node.val);
                    if (node.left != null){
                        stack2.add(node.left);
                    }
                    if (node.right != null){
                        stack2.add(node.right);
                    }
                }else {
                    result.add(nodeList);
                    nodeList = new ArrayList<>();
                    level++;
                }


            }else {
                if(!stack2.isEmpty()){
                    node = stack2.pop();
                    nodeList.add(node.val);
                    if (node.right != null){
                        stack1.add(node.right);
                    }
                    if (node.left != null){
                        stack1.add(node.left);
                    }
                }else {
                    result.add(nodeList);
                    nodeList = new ArrayList<>();
                    level++;
                }
            }
            if(stack1.isEmpty() && stack2.isEmpty()){
                result.add(nodeList);
                level = 0;
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> nodeList = new ArrayList<>();
        if(pRoot == null){
            return result;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        int level = 1;
        stack1.add(pRoot);
        TreeNode node = null;
        while ((!stack1.isEmpty() || !stack2.isEmpty()) && level > 0){
            if(level % 2 != 0){
                node = stack1.pop();
                nodeList.add(node.val);
                if (node.left != null){
                    stack2.add(node.left);
                }
                if (node.right != null){
                    stack2.add(node.right);
                }
                if(stack1.isEmpty()){
                    result.add(nodeList);
                    nodeList = new ArrayList<>();
                    level++;
                }


            }else {
                node = stack2.pop();
                nodeList.add(node.val);
                if (node.right != null){
                    stack1.add(node.right);
                }
                if (node.left != null){
                    stack1.add(node.left);
                }
                if(stack2.isEmpty()){
                    result.add(nodeList);
                    nodeList = new ArrayList<>();
                    level++;
                }
            }
        }
        return result;
    }
}
