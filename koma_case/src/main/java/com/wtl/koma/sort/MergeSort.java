package com.wtl.koma.sort;

/**
 * @Description 归并排序O(log以2为底n)
 * @Author WTL
 * @Date 2021/1/19
 */
public class MergeSort {

    /**
     * 二分数组排序然后进行外排
     * @param arr
     * @param b 开始下标
     * @param e 结束下标
     */
    public static void dichotomySort(int[] arr, int b, int e){
        if(b == e){
            return;
        }
        //获取中间下标（e - b获取本次中间的位置在第几位，加b获取中间位置的下标）
        int mid = b + ((e - b) >> 1);
        //排序前一般的数组顺序（传参的开始下标到中间下标排序）
        dichotomySort(arr, b, mid);
        //排序后一半的数组顺序（传参的中间下标到最后下标排序）
        dichotomySort(arr, mid + 1, e);
        //最后进行两半数组的合并排序
        merge(arr, b, mid, e);
    }

    /**
     * 两半数组进行合并排序
     * @param arr
     * @param b
     * @param mid
     * @param e
     */
    public static void merge(int[] arr, int b, int mid, int e){
        //初始化数组长度
        int[] tmp = new int[e - b + 1];
        int i = 0;
        //赋值前一半遍历的起始下标
        int a1 = b;
        //赋值后一半遍历的起始下标
        int a2 = mid + 1;
        //循环比较两半的数据，当有一半的下标到达上限后停止循环比较
        while (a1 <= mid && a2 <= e){
            //比较两半的数组哪一边的数小将哪一边的数获取出来放到临时数组中并下标+1
            tmp[i++] = arr[a1] < arr[a2] ? arr[a1++] : arr[a2++];
        }

        /**
         * 如果在上边的循环中某一半的下标还没到最后一位，那么将这一半剩余的数直接放入临时数组中
         */
        while (a1 <= mid){
            tmp[i++] = arr[a1++];
        }
        while (a2 <= e){
            tmp[i++] = arr[a2++];
        }

        //将排好的数组放入原数组相应的范围
        for (int n = 0; n < tmp.length; n++){
            arr[b++] = tmp[n];
        }
    }

    public static void main(String[] args) {
//        System.out.println(0 + (1-0) >> 1);
        int[] arr = {2,4,9,3,1,5};
        dichotomySort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }

}
