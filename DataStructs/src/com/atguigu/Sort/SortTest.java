package com.atguigu.Sort;

import com.atguigu.tree.HeapSort;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author z
 * @createdate 2019-08-03 10:47
 */
public class SortTest {

    //测试用例
    int[] array = Common.randomIntArray(100000);
    @Test

    public  void test(){
        int[] array_duplicate;
        System.out.println("-----------------------------------------");
        long start = System.currentTimeMillis();
        long end=0;
        System.out.printf("十万个数冒泡排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //BubbleSort.bubbleSort_v1(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数冒泡排序用时： %d ms\n",end-start);
        System.out.printf("十万个数冒泡排序用时： %g s\n",(end-start)/1000.0);

        start = System.currentTimeMillis();
        System.out.printf("十万个数冒泡排序（加强版）开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //BubbleSort.bubbleSort_v2(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数冒泡排序（加强版）用时： %d ms\n",end-start);
        System.out.printf("十万个数冒泡排序（加强版）用时： %g s\n",(end-start)/1000.0);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        start = System.currentTimeMillis();
        System.out.printf("十万个数选择排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //SelectSort.selectSort(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数选择排序用时： %d ms\n",end-start);
        System.out.printf("十万个数选择排序用时： %g s\n",(end-start)/1000.0);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        start = System.currentTimeMillis();
        System.out.printf("十万个数插入排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //InsertSort.insertSort_v1(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数插入排序用时： %d ms\n",end-start);
        System.out.printf("十万个数插入排序用时： %g s\n",(end-start)/1000.0);

        start = System.currentTimeMillis();
        System.out.printf("十万个数插入排序（加强版）开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //InsertSort.insertSort_v2(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数插入排序（加强版）用时： %d ms\n",end-start);
        System.out.printf("十万个数插入排序（加强版）用时： %g s\n",(end-start)/1000.0);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        start = System.currentTimeMillis();
        System.out.printf("十万个数希尔（交换）排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //ShellSort.sellSort_v1(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数希尔（交换）排序用时： %d ms\n",end-start);
        System.out.printf("十万个数希尔（交换）排序排序用时： %g s\n",(end-start)/1000.0);

        start = System.currentTimeMillis();
        System.out.printf("十万个数希尔（移位）排序排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        ShellSort.sellSort_v2(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数希尔（移位）排序排序用时： %d ms\n",end-start);
        System.out.printf("十万个数希尔（移位）排序排序用时： %g s\n",(end-start)/1000.0);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        start = System.currentTimeMillis();
        System.out.printf("十万个数快速排序1排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //QuickSort1.sort(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数快速排序1排序用时： %d ms\n",end-start);
        System.out.printf("十万个数快速排序1排序排序用时： %g s\n",(end-start)/1000.0);

        start = System.currentTimeMillis();
        System.out.printf("十万个数快速排序2排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //QuickSort2.sort(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数快速排序2排序用时： %d ms\n",end-start);
        System.out.printf("十万个数快速排序2排序排序用时： %g s\n",(end-start)/1000.0);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        start = System.currentTimeMillis();
        System.out.printf("十万个数基数排序排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
       // int arr[] = { 53, 3, 542, 748, 14, 214};
       // RadixSort.sort(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数基数排序排序用时： %d ms\n",end-start);
        System.out.printf("十万个数基数排序排序排序用时： %g s\n",(end-start)/1000.0);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        start = System.currentTimeMillis();
        System.out.printf("十万个数归并排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        //int arr[] = { 53, 3, 542, 748, 14, 214};
        int arr1[]=new int[array_duplicate.length];
        MergeSort.sort(array_duplicate,0,array_duplicate.length-1,arr1);
        //System.out.println(Arrays.toString(arr));
        end = System.currentTimeMillis();
        System.out.printf("十万个数归并排序排序用时： %d ms\n",end-start);
        System.out.printf("十万个数归并排序排序排序用时： %g s\n",(end-start)/1000.0);

        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        start = System.currentTimeMillis();
        System.out.printf("十万个数堆排序排序开始...\n");
        array_duplicate= Arrays.copyOf(array,array.length);
        HeapSort.heapSort(array_duplicate);
        end = System.currentTimeMillis();
        System.out.printf("十万个数堆排序排序用时： %d ms\n",end-start);
        System.out.printf("十万个数堆排序排序排序用时： %g s\n",(end-start)/1000.0);
    }
}
