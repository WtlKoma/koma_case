package com.wtl.koma.sort;

/**
 * @Description 冒泡排序O(n平方)
 * @Author WTL
 * @Date 2021/1/19
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = i+1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,3,2,6,7,1,9};
        bubbleSort(arr);
        int i = 0;
        while (i < arr.length){
            System.out.println(arr[i++]);
        }
    }

}
