package com.atguigu.Sort;

/**
 * @author z
 * @createdate 2019-08-03 9:28
 */
public class BubbleSort {
    //基础版
    public static void bubbleSort_v1(int[] arr) {
        int tmp=0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
    //加强版
    public static void bubbleSort_v2(int[] arr) {
        boolean flag = false;
        int tmp=0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag=true;
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            //如果没进入比较，则表示已经是有序数组，中断排序即可
            if(!flag){
                break;
            }else {
                flag=false;
            }
        }
    }
}
