package com.atguigu.tree;

import org.junit.Test;

/**
 * @author z
 * @createdate 2019-08-15 11:14
 */
public class BinaryTreeTest {
    @Test
    public void test() {
        BinaryTree b = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode n1 = new HeroNode(2, "吴用");
        HeroNode n2 = new HeroNode(3, "玉麒麟");
        HeroNode n3 = new HeroNode(4, "关胜");

        b.setRoot(root);
        root.setLeft(n1);
        root.setRight(n2);
        n1.setLeft(n3);
        b.proOrder();

        b.findproOrder(5);
    }
}

class BinaryTree {
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    private HeroNode root;

    //前序遍历
    public void proOrder() {
        if (this.root != null) {
            this.root.proOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //前序查找
    public void findproOrder(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.findproOrder(no);
            if (heroNode != null)
                System.out.println(heroNode);
            else
                System.out.println("未知的英雄编号：" + no);
        } else {
            System.out.println("二叉树为空");
        }
    }

    //中序查找
    public void findinfixOrder(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.findinfixOrder(no);
            if (heroNode != null)
                System.out.println(heroNode);
            else
                System.out.println("未知的英雄编号：" + no);
        } else {
            System.out.println("二叉树为空");
        }
    }

    //后序查找
    public void findpostOrder(int no) {
        if (this.root != null) {
            HeroNode heroNode = this.root.findpostOrder(no);
            if (heroNode != null)
                System.out.println(heroNode);
            else
                System.out.println("未知的英雄编号：" + no);
        } else {
            System.out.println("二叉树为空");
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right; //默认null

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                /*", left=" + left +
                ", right=" + right +*/
                '}';
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //编写前序遍历的方法
    public void proOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.proOrder();
        }
        if (this.right != null) {
            this.right.proOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //编写前序查找的方法
    public HeroNode findproOrder(int no) {
        if (this.no == no) {
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        HeroNode h = null;
        if (this.left != null) {
            h = this.left.findproOrder(no);
        }
        if (h != null) {
            return h;
        }
        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            h = this.right.findproOrder(no);
        }
        return h;
    }

    //编写中序查找的方法
    public HeroNode findinfixOrder(int no) {
        HeroNode h = null;
        if (this.left != null) {
            h = this.left.findinfixOrder(no);
        }
        if (h != null) {
            return h;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            h = this.right.findinfixOrder(no);
        }
        return h;
    }

    //编写后序查找的方法
    public HeroNode findpostOrder(int no) {
        HeroNode h = null;
        if (this.left != null) {
            h = this.left.findpostOrder(no);
        }
        if (h != null) {
            return h;
        }
        if (this.right != null) {
            h = this.right.findpostOrder(no);
        }
        if (h != null) {
            return h;
        }
        if (this.no == no) {
            return this;
        }
        return null;
    }
}
