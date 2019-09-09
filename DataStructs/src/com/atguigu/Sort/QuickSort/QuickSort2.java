package com.atguigu.Sort.QuickSort;

import com.atguigu.Sort.Common;

/**
 * @author z
 * @createdate 2019-08-06 9:02
 */
public class QuickSort2 {
    // 我们的算法类不允许产生任何实例
    private QuickSort2() {
    }

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    private static int partition(int[] arr, int l, int r) {
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        Common.swap(arr, l, (int) (Math.random() * (r - l + 1)) + 1);

        int v = arr[l];
        int i = l + 1, j = r; // arr[l+1...i) <= v; arr(j...r] >= v
        while (true) {
            // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
            // 思考一下为什么?
            while (i <= r && arr[i] < v) i++;
            // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
            // 思考一下为什么?
            while (j >= l + 1 && arr[j] > v) j--;
            // 对于上面的两个边界的设定, 有的同学在课程的问答区有很好的回答:)
            // 大家可以参考: http://coding.imooc.com/learn/questiondetail/4920.html
            if (i > j) break;
            Common.swap(arr, i, j);
            i++;
            j--;
        }
        Common.swap(arr, l, j);
        return j;
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void sort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }
}
