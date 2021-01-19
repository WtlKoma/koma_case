package com.wtl.koma.sort;

/**
 * @Description 插入排序最好的情况为O(n)，最坏的情况为O(n平方)
 * @Author WTL
 * @Date 2021/1/19
 */
public class InsertSort {

    public static void insertSort(int[] arr){
        int n = 0;
        int tmp;
        //循环所需要排序的数组
        for (int i = 1; i < arr.length; i++) {
            n = i - 1;
            //和当前循环的前面的数组进行比较如果前边的大于当前的则交换位置直到当前的大于前一个停止
            while (n > -1 && arr[n] > arr[n+1]){
                //a^b^b = a              ^(异或)
                arr[n] = arr[n] ^ arr[n+1];
                arr[n+1] = arr[n] ^ arr[n+1];
                arr[n] = arr[n] ^ arr[n+1];
                /*tmp = arr[n];
                arr[n] = arr[n+1];
                arr[n+1] = tmp;*/
                n--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,3,2,6,7,1,9};
        insertSort(arr);
        int i = 0;
        while (i < arr.length){
            System.out.println(arr[i++]);
        }
    }

}
