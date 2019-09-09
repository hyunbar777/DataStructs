package com.atguigu.queue;

/**
 * @author z
 * @createdate 2019-07-24 9:12
 */
public class CircleArrayQueue {
    public static void main(String[] args) {

    }
}

class CircleArray {
    //数组最大容量
    private int maxSize;
    //指向队列的第一个元素，也就是说 arr[front] 就是队列的第一个元素
    //rear 的初始值 = 0
    private int front;
    //rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    // 队列尾
    private int rear;
    // 该数据用于存放数据, 模拟队列
    private int[] arr;

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列不能为空");
        }
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列不能为空");
        }
        // 思路：从front开始遍历，遍历多少个元素
        for (int i = front; i <front+size() ; i++) {

        }
    }
    public  int size(){
        return (rear-front+maxSize)%maxSize;
    }
}