package com.atguigu.Sort;

/**
 * @author z
 * @createdate 2019-08-03 20:30
 */
public class SelectSort {
    //O(n2)
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            //假定最小值为i
            int minIndex = i;
            //j从i+1开始
            for (int j = i+1; j <arr.length ; j++) {
                //如果假定的最小值大于后面一个元素即arr[j],则吧minIndex指向arr[j]
                //否则不进入if判断
                if(arr[minIndex]>arr[j]){
                    minIndex = j;
                }
            }
            //如果minIndex没有变化，即，minIndex就是arr[i]，不需交换
            if(minIndex!=i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }
}
