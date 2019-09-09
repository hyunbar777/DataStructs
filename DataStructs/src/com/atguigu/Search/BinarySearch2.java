package com.atguigu.Search;

/**
 * @author z
 * @createdate 2019-08-09 20:37
 */
public class BinarySearch2 {
    public static int search(Comparable[] arr, Comparable o) {
        return find(arr,0,arr.length-1,o);
    }

    private static int find(Comparable[] arr, int l, int r, Comparable o) {
        if(l>r){
            return -1;
        }
        int mid=(r+l)>>>1;
        if(arr[mid].compareTo(o)==0){
            return mid;
        }else if(arr[mid].compareTo(o)<0){
            return find(arr,mid+1,r,o);
        }else{
            return find(arr,l,mid+1,o);
        }
    }
}
