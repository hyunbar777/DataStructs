package com.atguigu.Sort.QuickSort;

import com.atguigu.Sort.Common;
import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * @author z
 * @createdate 2019-08-06 9:02
 */
public class QuickSort1 {
    // 我们的算法类不允许产生任何实例
    private  QuickSort1(){}
    // 对arr[low...high]部分进行partition操作
    // 返回p, 使得arr[low...p-1] < arr[p] ; arr[p+1...high] > arr[p]
    private static int partition(int[] arr, int low, int high){
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        Common.swap(arr,low,(int)(Math.random() * (high - low + 1)) + 1);

        int v = arr[low];
        int j = low;// arr[l+1...j] < v ; arr[j+1...i) > v
        for (int i =low+1; i <=high ; i++) {
            if(arr[i]<v){
                j++;
                Common.swap(arr,i,j);
            }
        }
        Common.swap(arr,low,j);
        return j;
    }
    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void sort(int[] arr, int low, int high){
        if( low >= high )
            return;
        int p=partition(arr,low,high);
        sort(arr,low,p-1);
        sort(arr,p+1,high);
    }
    public static  void sort(int[] arr){
        int n = arr.length;
        sort(arr,0,n-1);
    }
}
