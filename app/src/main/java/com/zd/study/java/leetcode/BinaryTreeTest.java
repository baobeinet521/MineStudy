package com.zd.study.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BinaryTreeTest {
    private static int count = 0;
    private static int preIndex = 0;
    private static int inIndex = 0;
    private static int afterIndex = 0;

    public static void main(String[] args) {
        count = 0;
        preIndex = 0;
        inIndex = 0;
        afterIndex = 0;
        TreeNode inOrder = new TreeNode(3);
        TreeNode leftOrder = new TreeNode(9);
        TreeNode rightOrder = new TreeNode(20);

        TreeNode rightOrderL = new TreeNode(15);
        TreeNode rightOrderR = new TreeNode(7);

        inOrder.left = leftOrder;
        inOrder.right = rightOrder;

        rightOrder.left = rightOrderL;
        rightOrder.right = rightOrderR;

        List<List<Integer>> result = levelOrder(inOrder);
        System.out.println("最终结果是    " + result);

//        int[][] data = threeOrders(null);
//        System.out.println("最终结果是    " + Arrays.toString(data));
    }

    public static int[][] threeOrders(TreeNode root) {

        int count = countNode(root);
        int[][] res = new int[3][count];

        if (root == null) {
            return res;
        }

        int[] pre = preOrderTraversal(root, res[0]);
//        res[0] = pre;

        int[] inOrderVec = inorderTraversal(root, res[1]);
//        res[1] = inOrderVec;

        int[] afterVecOrder = afterOrderTraversal(root, res[2]);
//        res[2] = afterVecOrder;

        return res;

    }

    public static int countNode(TreeNode root) {
        if (root == null) {
            return count;
        }
        count++;
        countNode(root.left);
        countNode(root.right);
        return count;
    }

    /**
     * 前序
     *
     * @param root
     * @return
     */
    public static int[] preOrderTraversal(TreeNode root, int[] res) {
        if (root == null) {
            return null;
        }
        res[preIndex++] = root.val;
        preOrderTraversal(root.left, res);
        preOrderTraversal(root.right, res);
        return res;
    }

    /**
     * 中序
     *
     * @param root
     * @return
     */
    public static int[] inorderTraversal(TreeNode root, int[] res) {
        if (root == null) {
            return null;
        }
        inorderTraversal(root.left, res);
        res[inIndex++] = root.val;
        inorderTraversal(root.right, res);
        return res;
    }

    /**
     * 后续
     *
     * @param root
     * @return
     */
    public static int[] afterOrderTraversal(TreeNode root, int[] res) {
        if (root == null) {
            return null;
        }
        afterOrderTraversal(root.left, res);
        afterOrderTraversal(root.right, res);
        res[afterIndex++] = root.val;
        return res;
    }


    //非递归实现  先序遍历
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stackNode = new Stack<>();
        stackNode.push(root);
        while (!stackNode.isEmpty()){
            TreeNode popNode = stackNode.pop();
            result.add(popNode.val);
            if (popNode.right != null){
                stackNode.push(popNode.right);
            }
            if (popNode.left != null){
                stackNode.push(popNode.left);
            }

        }
        return result;
    }

    //非递归实现 中序
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    //非递归实现后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack1 = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            stack1.push(cur);
            if(cur.left != null){
                stack.push(cur.left);
            }
            if (cur.right != null){
                stack.push(cur.right);
            }
        }
        while (!stack1.isEmpty()){
            TreeNode resNode = stack1.pop();
            result.add(resNode.val);
        }
        return result;
    }

    //层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        List<Integer> nodeList = new ArrayList<>();
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        levelMap.put(root, 0);
        result.add(nodeList);
        int curLevel = 0;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (curLevel != curNodeLevel) {
                nodeList = new ArrayList<>();
                result.add(nodeList);
                curLevel++;
            }
            nodeList.add(cur.val);
            if(cur.left != null){
                queue.add(cur.left);
                levelMap.put(cur.left, curNodeLevel + 1);
            }
            if(cur.right != null){
                queue.add(cur.right);
                levelMap.put(cur.right, curNodeLevel + 1);
            }
        }
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            List<Integer> temp = result.get(i);
            data.add(temp.get(temp.size() - 1));
        }
        return result;
    }

    //右视图，使用了hashMap记录节点层数
    public static List<Integer> rightSideViewH(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        if(root == null){
            return data;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> nodeList = new ArrayList<>();
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        levelMap.put(root, 0);
        result.add(nodeList);
        int curLevel = 0;
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (curLevel != curNodeLevel) {
                nodeList = new ArrayList<>();
                result.add(nodeList);
                curLevel++;
            }
            nodeList.add(cur.val);
            if(cur.left != null){
                queue.add(cur.left);
                levelMap.put(cur.left, curNodeLevel + 1);
            }
            if(cur.right != null){
                queue.add(cur.right);
                levelMap.put(cur.right, curNodeLevel + 1);
            }
        }

        for (int i = 0; i < result.size(); i++){
            List<Integer> temp = result.get(i);
            data.add(temp.get(temp.size() - 1));
        }
        return data;
    }
    //二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> treeQueue = new LinkedList<>();
        treeQueue.add(root);
        TreeNode curEndNode = root;
        TreeNode nextEndNode = null;
        while (!treeQueue.isEmpty()){
            TreeNode curNode = treeQueue.poll();
            if(curNode.left != null){
                treeQueue.add(curNode.left);
                nextEndNode = curNode.left;
            }
            if(curNode.right != null){
                treeQueue.add(curNode.right);
                nextEndNode = curNode.right;

            }
            if (curEndNode == curNode){
                result.add(curEndNode.val);
                curEndNode = nextEndNode;
                nextEndNode = null;
            }

        }
        return result;
    }

    /**
     * 求二叉树的右视图
     * @param xianxu int整型一维数组 先序遍历
     * @param zhongxu int整型一维数组 中序遍历
     * @return int整型一维数组
     */
//    public int[] solve (int[] xianxu, int[] zhongxu) {
//        // write code here
//
//    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     *
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        double preTemp = -Double.MAX_VALUE;
        while (!stack.isEmpty() || temp != null){
            if (temp != null){
                stack.push(temp);
                temp = temp.left;
            } else {
                TreeNode node = stack.pop();
                if(node.val <= preTemp){
                    return false;
                }else {
                    preTemp = node.val;
                }
                temp = node.right;
            }

        }
        return true;
    }
}
