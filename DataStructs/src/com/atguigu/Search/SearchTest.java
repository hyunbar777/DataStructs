package com.atguigu.Search;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author z
 * @createdate 2019-08-09 20:07
 */
public class SearchTest {
    @Test
    public void sequenceSearchTest(){
        int i = SequenceSearch.sequenceSearch(new Integer[]{5, 9, -1, -19, 34, 6}, -19);
        System.out.println(i);
    }
    @Test
    public void binarySearch1Test(){
        int i = BinarySearch.search(new Integer[]{1,2,3,4,5,6}, 5);
        System.out.println(i);
    }
    @Test
    public void binarySearch2Test(){
        Integer[] arr= new Integer[1000000];
        for (int i = 0; i <1000000 ; i++) {
            arr[i]=i;
        }
        long start = System.currentTimeMillis();

        int i = BinarySearch2.search(arr, 987654);

        long end= System.currentTimeMillis();
        System.out.printf("递归搜索用时： %d ms\n",end-start);
        System.out.printf("递归搜索用时： %g s\n",(end-start)/1000.0);
        System.out.println(i);
    }
    @Test
    public void insertbinarySearchTest(){
        int[] arr= new int[1000000];
        for (int i = 0; i <1000000 ; i++) {
            arr[i]=i;
        }
        long start = System.currentTimeMillis();

        int i = InsertValueSearch.search(arr, 987654);

        long end= System.currentTimeMillis();
        System.out.printf("差值搜索用时： %d ms\n",end-start);
        System.out.printf("差值搜索用时： %g s\n",(end-start)/1000.0);
        System.out.println(i);
    }
}
