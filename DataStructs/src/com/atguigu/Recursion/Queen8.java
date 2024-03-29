package com.atguigu.Recursion;

/**
 * @author z
 * @createdate 2019-08-02 19:45
 */
public class Queen8 {
    //定义一个max表示共有多少个皇后8
    final static int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    static int[] arr = new int[max];
    //记录不冲突的次数
     static int count;
    //总判断次数
    static int sumCount;

    public static void main(String[] args) {
        check(0);
        System.out.printf("共有%d种走法，一共判断%d次\n",count,sumCount);
    }

    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
    public static void check(int n) {
        //n = 8 , 其实8个皇后就既然放好
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n , 放到该行的第1列
            arr[n]=i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){ // 不冲突
                //接着放n+1个皇后,即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行得 后移的一个位置
        }

    }

    //查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
    public static boolean judge(int n) {
        sumCount++;
        // 说明
        //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
        //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
        // n = 1  放置第 2列 1 n = 1 array[1] = 1
        // Math.abs(1-0) == 1  Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
        //3. 判断是否在同一行, 没有必要，n 每次都在递增
        for (int i = 0; i <n ; i++) {
            if(arr[i]==arr[n]||Math.abs(arr[n]-arr[i])==Math.abs(n-i)){
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    public static void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println();
    }
}
