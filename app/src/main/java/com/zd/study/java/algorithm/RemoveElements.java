package com.zd.study.java.algorithm;

public class RemoveElements {
    public static void main(String[] args){

    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    public int removeElement(int[] nums, int val) {
        if(nums == null){
            return 0;
        }
        if(nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int temp = 0;
        while (left != right){
            if(nums[left] == val){
                if(nums[right] != val){
                    temp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = temp;
                    left++;
                }
                if(left < right){
                    right--;
                }
            }else {
                left++;
            }
        }
        if(nums[left] == val){
            if(left < nums.length ){
                return left;
            }else {
                return 0;
            }
        }else {
            return left + 1;
        }
    }
}
