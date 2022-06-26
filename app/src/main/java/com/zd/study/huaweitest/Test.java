package com.zd.study.huaweitest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.zd.study.java.algorithm.TreeNode;
import com.zd.study.java.bean.Goods;
import com.zd.study.java.bean.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {

//        int[] arr = new int[] {};
//        int[] arr2 = new int[] {1};
//        String a = "()[]{}";
//        boolean result  = isValid(a);
//        System.out.println("" + result);

        TreeNode node1 = new TreeNode(15, null, null);
        TreeNode node2 = new TreeNode(17, null, null);
        TreeNode node3 = new TreeNode(20, node1, node2);
        TreeNode node4 = new TreeNode(9, null, null);
        TreeNode nodeRoot = new TreeNode(3, node4, node3);
        int depth = minDepth1(nodeRoot);
        System.out.println(depth);
    }

    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;
        if(root.left != null){
            minDepth = Math.min(minDepth(root.left), minDepth);
        }
        if(root.right != null){
            minDepth = Math.min(minDepth(root.right), minDepth);
        }
        return minDepth + 1;
    }

    public static int minDepth1(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null){
                    return depth + 1;
                }else {
                    if (node.left != null){
                        queue.offer(node.left);
                    }
                    if (node.right != null){
                        queue.offer(node.right);
                    }
                }
                size--;
            }
            depth++;
        }
        return depth;
    }

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
       return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    //广度优先
    public int maxDepth1(TreeNode root) {
        if(root == null){
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }
    public static boolean isValid (String s) {
        if(s == null || s.length() == 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        stack.push(chars[0]);
        for (int i = 1; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else {
                    stack.push(s.charAt(i));
                }
            }else if(s.charAt(i) == ']'){
                if(!stack.isEmpty() && stack.peek() == '['){
                    stack.pop();
                }else {
                    stack.push(s.charAt(i));
                }
            }else if (s.charAt(i) == '}'){
                if(!stack.isEmpty() && stack.peek() == '{'){
                    stack.pop();
                }else {
                    stack.push(s.charAt(i));
                }
            }else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public ListNode EntryNodeOfLoop(ListNode pHead){
        if(pHead == null || pHead.next == null){
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead.next.next;
        while (slow != fast){
            if (fast == null && fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = pHead;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return  slow;
    }
    public static void merge(int A[], int m, int B[], int n) {
        int[] result = new int[m + n];
        int k = 0;
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex < m && bIndex < n){
            if(A[aIndex] <= B[bIndex]){
                result[k++] = A[aIndex++];
            } else {
                result[k++] = B[bIndex++];
            }
        }
        while (aIndex < m){
            result[k++] = A[aIndex++];
        }
        while (bIndex < n){
            result[k++] = B[bIndex++];
        }

        for (int i = 0; i < m + n; i++){
            A[i] = result[i];
        }
        System.out.println("");
    }

    public static int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int sum  = 0;
        int max = array[0];
        for (int i = 0; i < array.length; i++){
            sum = Math.max(sum + array[i], array[i]);
            max = Math.max(sum, max);
        }
        return max;
    }
    public static int maxLength1 (int[] arr) {
        int result = 0;
        if(arr == null || arr.length == 0){
            return result;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++){
            if(queue.contains(arr[i])){
                queue.poll();
            }
            queue.offer(arr[i]);
            result = Math.max(result, queue.size());
        }
        return result;
    }

    public static int maxLength (int[] arr) {
        int result = 0;
        if(arr == null || arr.length == 0){
            return result;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < arr.length; i++){
            if(map.containsKey(arr[i])){
                start = Math.max(start, map.get(arr[i]) + 1);
            }
            result = Math.max(result, i - start + 1);
            map.put(arr[i], i);
        }
        return result;
    }

    public static void test15(){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String info = in.next();
            if(info.isEmpty() || info.length() <= 8){
                System.out.println("NG");
                continue;
            }
            if(!getMatch(info)){
                System.out.println("NG");
                continue;
            }
            if(checkSubStr(info, 0, 3)){
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }
    }

    public static boolean checkSubStr(String str, int left, int right){
        if(right >= str.length()){
            return false;
        }
        if (str.substring(right).contains(str.substring(left, right))){
            return false;
        }else {
            return checkSubStr(str, left + 1, right + 1);
        }

    }

    //椒盐是否满足正则
    public static boolean getMatch(String str){
        int count = 0;
        Pattern p1 = Pattern.compile("[A-Z]");
        if(p1.matcher(str).find()){
            count++;
        }
        Pattern p2 = Pattern.compile("[a-z]");
        if(p2.matcher(str).find()){
            count++;
        }
        Pattern p3 = Pattern.compile("[0-9]");
        if(p3.matcher(str).find()){
            count++;
        }

        Pattern p4 = Pattern.compile("[^A-Za-z0-9]");
        if(p4.matcher(str).find()){
            count++;
        }
        return count >= 3 ? true : false;
    }
    public static void test14(){
        Scanner in = new Scanner(System.in);
        String info = in.nextLine();
        if(info.isEmpty()){
            System.out.print("");
        }
        char[] data = info.toCharArray();
        for (int i = 0; i < data.length; i++){
            if(data[i] >= 'A' && data[i] <= 'Z'){
                if(data[i] + 32 != 'z'){
                    data[i] += 32 + 1;
                }else {
                    data[i] = 'a';
                }
            }else if(data[i] >= 'a' && data[i] <= 'r'){
                data[i] = (char) ((data[i] - 'a') / 3 + 2 + '0');
            }else if(data[i] == 's'){
                data[i] = '7';
            }else if(data[i] >= 't' && data[i] <= 'v'){
                data[i] = '8';
            }else if(data[i] >= 'w' && data[i] <= 'z'){
                data[i] = '9';
            }
            System.out.print(data[i]);
        }

    }


    //动态规划
    public static void test13(){
        Scanner in = new Scanner(System.in);
        String info = in.nextLine();
        String[] dataInfos = info.split(" ");
        int moneyTotal = Integer.parseInt(dataInfos[0]);
        int buyTotal = Integer.parseInt(dataInfos[1]);
        int mainTotal = 0;
        if(moneyTotal <= 0 || buyTotal <= 0){
            System.out.print("0");
        }
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < buyTotal; i++){
            String data = in.nextLine();
            String[] strs = data.split(" ");
            int number = Integer.parseInt(strs[2]);
            Goods goods = new Goods(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), number);
            goodsList.add(goods);
            if(number > 0){
                goodsList.get(number - 1).setAttachmentOne(number - 1);
            }
        }
        int remainderMoney = moneyTotal;
        int satisfactionPre = 0;
        int satisfaction = 0;
        for (int i = mainTotal - 1; i >= 0; i--){
//            for (int j = )
            if (remainderMoney >= goodsList.get(i).values){
                //剩余的钱大于主件的价格，可以买该主件
                remainderMoney = moneyTotal - goodsList.get(i).values;
                satisfaction = Math.max(goodsList.get(i - 1).values * goodsList.get(i - 1).weight,goodsList.get(i).values * goodsList.get(i).weight);
            }
        }
        System.out.print(satisfaction+"");
    }
    /**
     * 输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。
     *
     * 数据范围：保证在 32 位整型数字范围内
     */
    //利用现有的api来做
    public static void test10Api(){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        String numInt = Integer.toBinaryString(num);
        char[] charArray = numInt.toCharArray();
        int total = 0;
        for (int i = 0; i < charArray.length; i++){
            if (charArray[i] == '1'){
                total++;
            }
        }
        System.out.print(total+"");
    }
    //无符号右移统计二进制中1的个数
    public static void test11(){
        Scanner in = new Scanner(System.in);
        int total = 0;
        int num = in.nextInt();
        for (int i = 0; i < 32; i++){
            if ((num & 1)  == 1){  //如果末位为1则计数
                total++;
            }else {
                num >>>= 1;//无符号右移
            }
        }
        System.out.print(total+"");
    }

    //和num - 1进行与运算，总会消除一个1直到num == 0
    public static void test12(){
        Scanner in = new Scanner(System.in);
        int total = 0;
        int num = in.nextInt();
        while (num != 0){
            num = num & (num - 1);
            total++;
        }
        System.out.print(total+"");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void test6(){
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String total = in.nextLine();
        while (in.hasNextLine()){
            list.add(in.nextLine());
        }
        list.sort(Comparator.naturalOrder());
        for (int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void test7(){
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String total = in.nextLine();
        while (in.hasNextLine()){
            list.add(in.nextLine());
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int index = 0;
                while (index < s1.length() && index < s2.length()){
                    if(s1.charAt(index) != s2.charAt(index)){
                        return (s1.charAt(index) > s2.charAt(index)) ? 1: -1;
                    }
                    index++;
                }
                if (s1.length() == s2.length()){
                    return 0;
                }else {
                    return (s1.length() > s2.length()) ? 1: -1;
                }
            }
        });
        for (int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static void test8(){
        Scanner in = new Scanner(System.in);
        PriorityQueue<String> queue = new PriorityQueue<>();
        String total = in.nextLine();
        while (in.hasNextLine()){
            queue.offer(in.nextLine());
        }
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

    public static void test5(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] listStr = str.split(" ");
        for (int i = listStr.length - 1; i >= 0 ; i--){
            System.out.print(listStr[i] + " ");
        }
    }
    public static void test4(){
        Scanner in = new Scanner(System.in);
        HashMap<Character, Integer> map = new HashMap<>();
        String str = in.nextLine();
        for (int i = 0; i < str.length(); i++){
            Character charData = str.charAt(i);
            if (!map.containsKey(charData)) {
                map.put(charData, 1);
            }
        }
        System.out.print(map.size());
    }
    public static void test3(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        List<Character> data = new ArrayList<>();
        for(int i = 0;i < str.length(); i++){
            if (data.contains(str.charAt(i))) {
                Character charD = str.charAt(i);
                data.remove(charD);
            }
            data.add(str.charAt(i));
        }
        for (int i = data.size() - 1; i >= 0; i--){
            System.out.print(data.get(i));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void test2(){
        Scanner in = new Scanner(System.in);
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> keys = new ArrayList<>();
        while (in.hasNextLine()) {
            String data = in.nextLine();
            if(data.contains(" ")){
                String[] strList = data.split(" ");
                int index = Integer.parseInt(strList[0]);
                int value = Integer.parseInt(strList[1]);
                if(map.containsKey(index)){
                    map.put(index, map.get(index) + value);
                }else {
                    keys.add(index);
                    map.put(index, value);
                }
            }
        }
        keys.sort(Comparator.naturalOrder());
        for (int i = 0; i < keys.size(); i++){
            System.out.println(keys.get(i) + " " + map.get(keys.get(i)));
        }
    }
    public static void test1(){
        Scanner in = new Scanner(System.in);
        float num = in.nextFloat();
        System.out.print(Math.round(num) + " ");

    }
    public static void primeNumber(){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for (int i = 2; i < num ; i ++){
            if(i > num / i){
                System.out.print(num + " ");
                break;
            }
            if(num % i == 0){
                System.out.print(i + " ");
                num = num / i;
                i = 1;
            }
        }
    }

    public static void sixteenToInt(){
        Scanner in = new Scanner(System.in);
        String finStr = "";
        while (in.hasNext()){
            finStr += in.nextLine();
        }
        int data = Integer.parseInt(finStr.substring(2, finStr.length()), 16);
        System.out.println(data);
    }

    public static void splitStr(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int strLength = str.length();
        int totalCount = strLength / 8;
        int count = strLength % 8;
        List<String> listStr = new ArrayList<>();
        for (int i = 0; i < totalCount; i++){
            String temp = str.substring(i * 8 , (i + 1) * 8);
            listStr.add(temp);
        }
        if(count > 0){
            String finalStr = str.substring(totalCount * 8 , strLength);
            int needCount = 8 - count;
            for (int i = 0; i < needCount; i++){
                finalStr += "0";
            }
            listStr.add(finalStr);
        }

        for (int i = 0; i < listStr.size(); i++){
            System.out.println(listStr.get(i));
        }


    }

    //排序生成的随机数
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void getDataAndSort(){
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        List<Integer> list = new ArrayList<>();
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if(!list.contains(a)){
                list.add(a);
            }
        }
        list.sort(Comparator.naturalOrder());
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).toString());
        }
    }

    //寻找字符串中 某一个字符串出现的次数
    public static void getStrCount(){
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine().toLowerCase();
        String str2 = in.nextLine();
        String fin = str1.replaceAll(str2," ");
        System.out.println(str1.length() - fin.length());
    }
    //获取最后一个字符串长度
    public static void getStrLength(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        if(str.isEmpty()){
            System.out.println(0);
            return;
        }
        String[] inStr = str.split(" ");
        System.out.println(inStr[inStr.length - 1].length());
    }
}
