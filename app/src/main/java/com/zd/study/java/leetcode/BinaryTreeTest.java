package com.zd.study.java.leetcode;

import java.util.Arrays;

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
        TreeNode inOrder = new TreeNode(1);
        TreeNode leftOrder = new TreeNode(2);
        TreeNode rightOrder = new TreeNode(3);

        inOrder.left = leftOrder;
        inOrder.right = rightOrder;

        int[][] data = threeOrders(null);
        System.out.println("最终结果是    " + Arrays.toString(data));
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
}
