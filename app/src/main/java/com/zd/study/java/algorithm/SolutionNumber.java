package com.zd.study.java.algorithm;

import java.util.Arrays;

public class SolutionNumber {
    public static void main(String[] args) {
//        changeData(-1, 4);
        int[] arr = {5,2,3,1,4};

//        int[] result = MySortSelect(arr);
//        int[] result = MySortBubble(arr);
//        int[] result = insertMySort(arr);
//        int[] result = MergeSortArray(arr);
        int[] result = partitionSortArray(arr);

        for(int i = 0;i < result.length; i++){
            System.out.print(result[i] + "  ");

        }
    }

    /**
     * 描述
     * 给定一个长度为 n 的数组，请你编写一个函数，返回该数组按升序排序后的结果。
     *
     * 数据范围： 0 \le n \le 10^50≤n≤10
     * 5
     *  ，数组中每个元素都满足 0 \le val \le 10^90≤val≤10
     * 9
     *
     * 要求：时间复杂度 O(nlogn)O(nlogn)，空间复杂度 O(n)O(n)
     *
     * 示例1
     * 输入：
     * [5,2,3,1,4]
     * 复制
     * 返回值：
     * [1,2,3,4,5]
     * @param arr
     * @return
     */
    public static int[] MySort (int[] arr) {
        // write code here
        if(arr == null){
            return  null;
        }
        int mid = arr.length >> 1;
        for(int i = 0; i  <= mid; i++){
            int midNum = arr[mid];
            if(arr[i] > midNum){
                int temp = arr[i];
                arr[i] = midNum;
                arr[mid] = temp;
            }
            if(arr[i] == midNum){
                int temp = arr[i + 1];
                arr[i + 1] = midNum;
                arr[mid] = temp;
            }
        }

        for (int j = mid + 1; j < arr.length; j++) {
            int midNum = arr[mid];
            if (arr[j] < midNum) {
//                changeData(arr[j], midNum);
                int temp = arr[j];
                arr[j] = midNum;
                arr[mid] = temp;
            }
            if(arr[j] == midNum){
//                changeData(arr[j],arr[mid + 1]);
                int temp = arr[mid + 1];
                arr[mid + 1] = arr[j];
                arr[j] = temp;
            }
        }
        return arr;
    }

    public static void changeData(int data1, int data2){
//        System.out.println("交换以前 data1= " + data1+"   data2 = " + data2);
        int temp = 0;
        temp = data1;
        data1 = data2;
        data2 = temp;
//        System.out.println("交换以后 data1= " + data1+"   data2 = " + data2);

    }

    /**
     * 选择
     * @param arr
     * @return
     */
    public static int[] MySortSelect(int[] arr) {
        // write code here
        if(arr == null){
            return  null;
        }
        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                int temp = 0;
                if(arr[i] >= arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

        }
        return arr;
    }

    /**
     * 冒泡
     * @param arr
     * @return
     */
    public static int[] MySortBubble(int[] arr) {
        // write code here
        if(arr == null){
            return  null;
        }
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                int temp = 0;
                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }


        }
        return arr;
    }

    /**
     * 插入
     * @param arr
     * @return
     */
    public static int[] insertMySort(int[] arr) {
        // write code here
        if(arr == null){
            return  null;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = 0;
                if (arr[i] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }

        }
        return arr;
    }

    /**
     * 归并排序
     * @param nums
     * @return
     */
    public static int[] MergeSortArray(int[] nums) {
        if(nums == null){
            return  null;
        }

        Arrays.sort(nums);
        return sortData(nums, 0, nums.length -1);

    }

    public static int[] sortData(int[] nums, int left,int right){
        if(left == right){
            return new int[] {nums[left]};
        }
        int mid = (left + right) / 2 ;
        System.out.println("mid 正常 " + mid);
        int[] numsLeft = sortData(nums, left, mid);
        System.out.println("左边数组返回   " + Arrays.toString(numsLeft));
        int[] numsRight = sortData(nums, mid + 1, right);
        System.out.println("右边数组返回   " +  Arrays.toString(numsRight));

        return mergeNums(numsLeft,numsRight);
    }

    public static int[] mergeNums(int[] leftN,int[] rightN){
        int pL = 0;
        int pR = 0;
        int pRes = 0;
        int[] res = new int[leftN.length + rightN.length];
        while (pL < leftN.length && pR < rightN.length){
            if(leftN[pL] < rightN[pR]){
                res[pRes++] = leftN[pL++];
            } else {
                res[pRes++] = rightN[pR++];
            }
        }

        while (pL < leftN.length){
            res[pRes++] = leftN[pL++];
        }
        while (pR < rightN.length){
            res[pRes++] = rightN[pR++];
        }
        return res;
    }


    /**
     * 快速排序 1.0 只有小于区域
     * @param nums
     * @return
     */
    public static int[] partitionSortArray(int[] nums) {
        if(nums == null){
            return  null;
        }

//        return partitionData(nums, 0 ,nums.length -1);
        return partitionDataNew(nums, 0 ,nums.length -1);
    }

    public static int[] partitionData(int[] nums, int start, int end){
        if(start == end){
            return nums;
        }

        int random = (int)Math.random()*(start + 1 - end) + end;
        int tempRanData = nums[random];
        nums[random] = nums[end];
        nums[end] = tempRanData;

        int startTag = start - 1;
        int index = start;

        while(index < end){
            if(nums[index] <= nums[end]){
                startTag++;
                int temp = nums[startTag];
                nums[startTag] = nums[index];
                nums[index] = temp;
            }
            index++;
        }
        int temp = nums[end];
        nums[end] = nums[startTag + 1];
        nums[startTag + 1] = temp;


        if(startTag >= start){
            partitionData(nums,start , startTag);
        }

        if(startTag + 2 <= end){
            partitionData(nums,startTag + 1 , end);
        }
        return nums;
    }


    /**
     * 3.0 版本有大于小于区域
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int[] partitionDataNew(int[] nums, int start, int end){
        if(start == end){
            return nums;
        }

        int randomPos = (int)Math.random()*(start + 1 - end) + end;
        int tempRanData = nums[randomPos];
        nums[randomPos] = nums[end];
        nums[end] = tempRanData;

        int lowTag = start - 1;
        int highTag = end;

        int position = start;

        while(position < highTag){
            if(nums[position] < nums[end]){
//                lowTag++;
                int temp = nums[lowTag + 1];
                nums[lowTag + 1] = nums[position];
                nums[position] = temp;
                lowTag++;
                position++;
            }else  if(nums[position] > nums[end]){
                int temp = nums[highTag -1];
                nums[highTag - 1] = nums[position];
                nums[position] = temp;
                highTag--;
            }else {
                position++;
            }

        }

        int temp = nums[end];
        nums[end] = nums[highTag];
        nums[highTag] = temp;
        highTag++;

        if(lowTag >= start){
            partitionData(nums,start , lowTag);
        }

        if(highTag <= end){
            partitionData(nums,highTag , end);
        }
        return nums;
    }
}
