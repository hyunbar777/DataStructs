package com.atguigu.Sort;

/**
 * @author z
 * @createdate 2019-08-03 9:28
 */
public class Common {

    /**
     *
     * @param n 多少个数
     * @return
     */
    public static int[] randomIntArray(int n){
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=(int)(Math.random()*n+1);
        }
        return  a;
    }
    public static Comparable[] randomComparableArray(int n){
        Comparable[] a = new Comparable[n];
        for (int i = 0; i < n; i++) {
            a[i]=(int)(Math.random()*n+1);
        }
        return  a;
    }
    public static void swap(int[] arr,int i,int j){
        int o = arr[i];
        arr[i]=arr[j];
        arr[j]=o;
    }
}
