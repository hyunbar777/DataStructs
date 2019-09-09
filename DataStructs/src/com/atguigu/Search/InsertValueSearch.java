package com.atguigu.Search;

/**
 * @author z
 * @createdate 2019-08-09 20:47
 */
public class InsertValueSearch {
    public static int search(int[] arr,int o){
       return   search(arr,o,arr.length-1,o);
    }
    private static int search(int[] arr,int l, int r, int o){
        if(l>r||o<arr[0]||o>arr[arr.length-1]){
            return -1;
        }
        int mid=l+(r-l)*(o - arr[l])/(arr[r]-arr[l]);
        int midVal = arr[mid];
        if (0 > midVal) { // 说明应该向右边递归
            return search(arr, mid + 1, l, o);
        } else if (o < midVal) { // 说明向左递归查找
            return search(arr, l, mid - 1, o);
        } else {
            return mid;
        }
    }
}
