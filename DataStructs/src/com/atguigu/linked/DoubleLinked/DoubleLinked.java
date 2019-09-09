package com.atguigu.linked.DoubleLinked;

import org.junit.Test;

/**
 * @author z
 * @createdate 2019-07-27 16:49
 */
public class DoubleLinked {
    private  HeroNode head = null;
    private  HeroNode last = null;
    @Test
    public void test() {
       /* insertNodeBefore(new HeroNode(1, "松江", "及时雨"));
        insertNodeBefore(new HeroNode(2, "武松", "行者"));
        insertNodeBefore(new HeroNode(3, "林冲", "豹子头"));*/
        insertNodeAfter(new HeroNode(1, "松江", "及时雨"));
        insertNodeAfter(new HeroNode(2, "武松", "行者"));
        insertNodeAfter(new HeroNode(3, "林冲", "豹子头"));

        deleteNode(2);
        deleteNode(1);
        deleteNode(3);
        printLinkedLast();
    }

    //插入新节点(插入最前面)
    public void insertNodeBefore(HeroNode newNode) {
        //如果插入的是第一个元素
        if(head==null){
            head = newNode;
            head.prev=null;
            head.next=null;
            last = head;
        }else {
            newNode.prev = null;
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }
    //插入新节点（插入最后面）
    public void insertNodeAfter(HeroNode newNode) {
        //如果插入的是第一个元素
        if(last==null){
            last = newNode;
            last.prev=null;
            last.next=null;
            head =last;
        }else {
            last.next = newNode;
            newNode.next = null;
            newNode.prev = last;
            last = newNode;
        }
    }
    //删除
    public  void deleteNode(int id){
        if (last == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = last;
        while (tmp!= null) {
            if(tmp.id==id){
                //只有一个节点，
                if(last.id==id && head.id==id){
                    head = null;
                    last = null;
                }
                //有两个节点，删除后一个
                else if(last.id == id && head.id!=id){
                    head.next=null;
                    last = head;
                }
                //有两个节点，删除前一个
                else if(head.id==id && last.id!=id){
                    last.prev = null;
                    head = last;
                }
                //有两个以上节点
                else{
                    //当前节点的前一个节点的下一个节点，指向当前节点的下一个节点
                    tmp.prev.next=tmp.next;
                    //当前节点的下一个节点的前一个节点，指向当前节点的前一个节点
                    tmp.next.prev=tmp.prev;
                }
                return;
            }
            tmp = tmp.prev;
        }
    }
    //打印双向链表(根据head打印)
    public void printLinkedHead() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head;
        while (tmp!= null) {
            System.out.println(show(tmp));
            tmp = tmp.next;
        }
    }
    //打印双向链表(根据head打印)
    public void printLinkedLast() {
        if (last == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = last;
        while (tmp!= null) {
            System.out.println(show(tmp));
            tmp = tmp.prev;
        }
    }
    public String show(HeroNode h) {
        return "{" +
                "本节点id=" + convertNode(h) + '\'' +
                "id=" + h.id +
                ", name='" + h.name + '\'' +
                ", nickName='" + h.nickName + '\'' +
                ", prev=" + convertNode(h.prev) + '\'' +
                ", next=" + convertNode(h.next) + '\'' +
                '}' + "\n";
    }

    //格式化HeroNode，直接输出地址值
    public String convertNode(HeroNode h) {
        return h == null ? null : h.toString().substring(h.toString().lastIndexOf('@'));
    }
}

class HeroNode {
    //值域
    public int id;
    public String name;
    public String nickName;
    //指针域
    public HeroNode next;
    public HeroNode prev;

    HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }
}
