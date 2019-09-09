package com.atguigu.Sort;

/**
 * @author z
 * @createdate 2019-08-03 20:57
 */
public class InsertSort {
    public  static void insertSort_v1(int[] arr){
        //从第二个元素开始，因为如果从第一个元素向前插入，第一个元素之前是没有数据的
        for (int i = 1; i < arr.length; i++) {
            //从第i个元素开始向前查找正确的插入位置
            //下标j>0并且当前元素值arr[j]小于arr[j-1]时，进入循环，即从小到大
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }

        }
    }
    public  static void insertSort_v2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            // 寻找元素arr[i]合适的插入位置
            int e = arr[i];
            int j = 0;
            // j保存元素e应该插入的位置，一次赋值，交换一次需要三次赋值
            for (j = i; j > 0 && arr[j - 1] > e; j--) {
                //此时arr[j],arr[j-1]是同一个数
                arr[j] = arr[j - 1];
            }
            //j是arr[i]应该插入的位置
            arr[j] = e;
        }
    }
}
