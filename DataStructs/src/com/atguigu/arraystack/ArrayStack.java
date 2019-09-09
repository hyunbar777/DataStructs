package com.atguigu.arraystack;

import org.junit.Test;

/**
 * @author z
 * @createdate 2019-07-29 15:55
 */
public class ArrayStack {
    @Test
    public void test() {
        Stack s = new Stack(5);

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);
        s.printStack();
        /*System.out.println("------------------------");
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.printStack();*/
    }
}

class Stack {
    //栈的最大容量
    static int maxSize;
    //用数组模拟
    static int[] arr;
    //栈顶，默认-1
    static int top = -1;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        //创建数组
        arr = new int[this.maxSize];
    }

    public static boolean isEmpty() {
        return top == -1;
    }

    public static boolean isFull() {
        return top == maxSize - 1;
    }

    public static void push(int value) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        arr[++top] = value;
    }

    public static int pop() {
        if (isEmpty()) {
            System.out.println("栈已空");
            return -1;
        }
        int tmp = arr[top];
        arr[top] = 0;
        top--;
        return tmp;
    }

    public static void printStack() {
        for (int i = top; i >=0 ; i--) {
            System.out.println(arr[i]);
        }
    }

}
