package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author z
 * @createdate 2019-08-21 18:23
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node root = createHuffmanTree(arr);

        preOrder(root);
    }
    public static  void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("空数");
        }
    }

    // 创建赫夫曼树的方法
    /**
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好后的赫夫曼树的root结点
     */
    public static Node createHuffmanTree(int[] arr){
        // 第一步为了操作方便
        // 1. 遍历 arr 数组
        // 2. 将arr的每个元素构成成一个Node
        // 3. 将Node 放入到ArrayList中
       ArrayList<Node> nodes = new ArrayList<>(arr.length);
        for (int v : arr) {
            nodes.add(new Node(v));
        }
        //我们处理的过程是一个循环的过程
        while (nodes.size()>1){
            //排序 从小到大
            Collections.sort(nodes);
            System.out.println(nodes);
            //取出根节点权值最小的两颗二叉树
            //(1) 取出权值最小的结点（二叉树）
            Node l = nodes.get(0);
            //(2) 取出权值第二小的结点（二叉树）
            Node r = nodes.get(1);
            //(3)构建一颗新的二叉树
            Node parent = new Node(l.value + r.value);
            parent.left = l;
            parent.right = r;
            //(4)从ArrayList删除处理过的二叉树
            nodes.remove(l);
            nodes.remove(r);
            //(5)将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼树的root结点
        return nodes.get(0);
    }
}
// 创建结点类
// 为了让Node 对象持续排序Collections集合排序
// 让Node 实现Comparable接口
class Node implements Comparable<Node>{
    int value;// 结点权值
    char c;
    Node left;
    Node right;
    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
