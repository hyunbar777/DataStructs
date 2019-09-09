package com.atguigu.Search;

/**
 * @author z
 * @createdate 2019-08-09 19:59
 */
public class SequenceSearch {
    public static int sequenceSearch(Comparable[] arr, Comparable o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
}
