package com.atguigu.Sort;

import java.util.Arrays;

/**
 * @author z
 * @createdate 2019-08-03 23:05
 */
public class ShellSort {

    // 希尔排序时， 对有序序列在插入时采用->交换法
    public static void sellSort_v1(int [] arr){
        int tmp = 0;
        for (int gap =arr.length/2 ; gap >0 ; gap/=2) {
            for (int i = gap; i <arr.length ; i++) {
                // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
                for (int j = i-gap; j >=0; j-=gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if(arr[j]<arr[j+gap]){
                        tmp= arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=tmp;
                    }
                }
            }
        }
    }
    //对交换式的希尔排序进行优化->移位法
    public static void sellSort_v2(int [] arr){
        for (int gap =arr.length/2 ; gap >0 ; gap/=2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int tmp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while (j - gap >= 0 && tmp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出for后，就给temp找到插入的位置
                    arr[j]=tmp;
                }
            }
        }
    }
    //演化
    private void evolution(){
        int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        int temp = 0;
        // 希尔排序的第1轮排序
        // 因为第1轮排序，是将10个数据分成了 5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("希尔排序1轮后=" + Arrays.toString(arr));//


        // 希尔排序的第2轮排序
        // 因为第2轮排序，是将10个数据分成了 5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("希尔排序2轮后=" + Arrays.toString(arr));//

        // 希尔排序的第3轮排序
        // 因为第3轮排序，是将10个数据分成了 2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
            for (int j = i - 1; j >= 0; j -= 1) {
                // 如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("希尔排序3轮后=" + Arrays.toString(arr));//
    }
}
