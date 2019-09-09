package com.atguigu.Sort.QuickSort;

import com.atguigu.Sort.Common;

/**
 * @author z
 * @createdate 2019-08-07 19:25
 */
public class QuickSort3 {
    private QuickSort3(){}

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void  sort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        Common.swap(arr, l, (int) (Math.random() * (r - l + 1)) + 1);

        int v = arr[l];

        int lt = l;// arr[l+1...lt] < v
        int gt = r + 1;// arr[gt...r] > v
        int i = l+1; // arr[lt+1...i) == v
        while (i<gt) {
           if(arr[i]<v){
               Common.swap(arr,i,lt+1);
               i++;
               lt++;
           }
           else if(arr[i]>v){
               Common.swap(arr,i,gt-l);
               gt--;
           }else {
               i++;
           }
        }
        Common.swap(arr, l, lt);
        sort(arr,l,lt-1);
        sort(arr,gt,r);
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }
}
