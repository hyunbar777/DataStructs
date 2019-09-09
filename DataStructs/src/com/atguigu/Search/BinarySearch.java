package com.atguigu.Search;

/**
 * @author z
 * @createdate 2019-08-09 20:15
 */
public class BinarySearch {
    public static int search(Comparable[] arr, Comparable o) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (r + l) >>> 1;
            int relTemp = arr[mid].compareTo(o);
            if (relTemp < 0) {
                l = mid + 1;
            } else if (relTemp > 0) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
