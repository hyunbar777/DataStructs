package com.atguigu.binarysorttree;

/**
 * @author z
 * @createdate 2019-09-02 21:04
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();

        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(9);
        binarySortTree.delNode(1);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);
        binarySortTree.delNode(7);

        System.out.println("root=" + binarySortTree.getRoot());
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }
    //删除结点
    public void delNode(int value) {
        if (root == null) return;
        //1.需求先去找到要删除的结点  targetNode
        Node targetNode=search(value);
        //如果没有找到要删除的结点
        if(targetNode==null){
            return;
        }
        //如果我们发现当前这颗二叉排序树只有一个结点
        if(root.left==null&&root.right==null){
            root = null;
            return;
        }
        //去找到targetNode的父结点
        Node parent=searchParent(value);
        //如果要删除的结点是叶子结点
        if(targetNode.left==null && targetNode.right==null){
            //判断targetNode 是父结点的左子结点，还是右子结点
            if(parent.left!=null && parent.left.value==value){
                parent.left=null;
            }else  if (parent.right!=null && parent.right.value==value){
                parent.right = null;
            }
        }else  if(targetNode.left!=null & targetNode.right!=null){
            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;
        }else {// 删除只有一颗子树的结点
            //如果要删除的结点有左子结点
            if(targetNode.left!=null){
                if(parent!=null){
                    //如果 targetNode 是 parent 的左子结点
                    if(parent.left.value==value){
                        parent.left = targetNode.left;
                    }else { //  targetNode 是 parent 的右子结点
                        parent.right = targetNode.left;
                    }
                }else {
                    root = targetNode.left;
                }

            }
            //如果要删除的结点有右子结点
            else  if(targetNode.right!=null){
                if(parent!=null){
                    //如果 targetNode 是 parent 的左子结点
                    if(parent.left.value==value){
                        parent.left = targetNode.right;
                    }else {//如果 targetNode 是 parent 的右子结点
                        parent.right = targetNode.right;
                    }
                }else {
                    root = targetNode.right;
                }
            }
        }
    }

    /**
     * 1. 返回的 以node 为根结点的二叉排序树的最小结点的值
     * 2. 删除node 为根结点的二叉排序树的最小结点
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    private int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left!=null){
            target = target.left;
        }
        //这时 target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    private Node searchParent(int value) {
        if(root==null)return null;
        else
            return root.searchParent(value);
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    public void add(Node node) {
        if (root != null) {
            root.add(node);
        } else {
            root = node;
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空");
        } else {
            root.infixOrder();
        }

    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {

        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    //添加结点的方法
    //递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的结点的值，和当前子树的根结点的值关系
        if (node.value < this.value) {
            if (this.left != null) {
                //如果当前结点左子结点为null
                this.left.add(node);
            } else {
                //递归的向左子树添加
                this.left = node;
            }
        } else {//添加的结点的值大于 当前结点的值
            if (this.right != null) {
                this.right.add(node);
            } else {
                //递归的向右子树添加
                this.right = node;
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //查找要删除的结点

    /**
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }
    /**
     *
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回null
     */
    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父结点，就返回
        if((this.left!=null && this.left.value==value)||
                (this.right!=null && this.right.value==value)){
            return this;
        }else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if(value<this.value&&this.left!=null){
                return  this.left.searchParent(value);//向左子树递归查找
            }else if(value>=this.value&& this.right!=null){
                return  this.right.searchParent(value);//向右子树递归查找
            }else{
                return null;// 没有找到父结点
            }
        }
    }
}