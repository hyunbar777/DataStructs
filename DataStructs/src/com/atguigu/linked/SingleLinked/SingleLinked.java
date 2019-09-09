package com.atguigu.linked.SingleLinked;

import org.junit.Test;

import java.util.Stack;

/**
 * @author z
 * @createdate 2019-07-26 20:48
 */
public class SingleLinked {
    //创建头结点
    private HeroNode head = new HeroNode(-1,null,null);
    //计数器，用于id的自增
    private  static  int count=0;
    @Test
    public void test(){
        //1.0插入节点
        insertNode(new HeroNode(++count,"亚瑟","坦克"));
        insertNode(new HeroNode(++count,"甄姬","法师"));
        insertNode(new HeroNode(++count,"后裔","射手"));
        insertNode(new HeroNode(++count,"赵云","刺客"));
        //2.0打印链表
        printLinked();

        //3.0根据id删除节点
        //deleteNode(2);

        //4.0更新数据，根据id
        //updateData(new HeroNode(2,"诸葛亮","法师"));

        //5.0根据id查找英雄
        //findData(2);

        //6.0查找链表的节点个数（有效的）
        //System.out.println(getLength());

        //7.0利用栈stack（）反转链表
        //reverseLinkedStack();

        //8.0反转链表
        //reverseLinked();

        //9.0找倒数第k个节点
        System.out.println(show( findLastIndexNode(2)));
        //printLinked();
    }

    //插入新节点
    public void insertNode(HeroNode newNode){
        if(head==null) {
            System.out.println("头结点不能为空");
            return;
        }
        if(newNode.getId()<1){
            System.out.println("请输入正确的编号");
            return;
        }
        HeroNode tmp = head;
        while (tmp.getNext()!=null){
            tmp=tmp.getNext();
        }
        tmp.setNext(newNode);
    }
    public  void printLinked(){
        if(head==null) {
            System.out.println("头结点不能为空");
            return;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return;
        }
        HeroNode tmp = head;
        System.out.println(head);
        while (tmp.getNext()!=null){
            System.out.println(show(tmp.getNext()));
            tmp=tmp.getNext();
        }
    }

    //删除指定id节点
    public void deleteNode(int id){
        if(head==null) {
            System.out.println("头结点不能为空");
            return;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return;
        }
        HeroNode tmp = head;
        while (tmp.getNext()!=null){
            if(tmp.getNext().getId()==id){
                tmp.setNext(tmp.getNext().getNext());
                return;

            }
            tmp=tmp.getNext();
        }
        System.out.println("未找该英雄，请选择正确的编号id");
    }
    //修改指定id节点的内容
    public void updateData(HeroNode node){
        if(head==null) {
            System.out.println("头结点不能为空");
            return;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return;
        }
        HeroNode tmp = head;
        while (tmp.getNext()!=null){
            if(tmp.getNext().getId()==node.getId()){
                //根据id更新节点内容，只需更新名字，id和next均不需更新
                tmp.getNext().setName(node.getName());
                tmp.getNext().setNickName(node.getNickName());
               return;
            }
            tmp=tmp.getNext();
        }
        System.out.println("未找该英雄，请选择正确的编号id");
    }
    //查找节点数据
    public HeroNode findData(int id){
        if(head==null) {
            System.out.println("头结点不能为空");
            return null;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return null;
        }
        HeroNode tmp = head;
        while (tmp.getNext()!=null){
            if(tmp.getNext().getId()==id){
                return tmp.getNext();
            }
            tmp=tmp.getNext();
        }
        System.out.println("未找该英雄，请选择正确的编号id");
        return  null;
    }
    //存入指定的文件
    public void saveAll(String fileName){

    }
    //从文件中加载链表
    public void readLinkedFromFile(String fileName){

    }
    //将单链表反转1
    public void reverseLinked(){
        if(head==null) {
            System.out.println("头结点不能为空");
            return ;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return ;
        }if(head.getNext().getNext()==null){
            System.out.println("就一个节点，无法反转");
            return ;
        }
        ////定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.getNext();
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode tmpHead = new HeroNode(-1,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        while (cur!=null){
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = cur.getNext();
            //将当前节点的next设置为，tmpHead的下一个节点

            cur.setNext(tmpHead.getNext());
            tmpHead.setNext(cur);
            //节点后移
            cur=next;
        }
        head.setNext(tmpHead.getNext());
        printLinked();
    }
    //将单链表反转2:stack()
    public void reverseLinkedStack(){
        Stack<HeroNode> stack = new Stack<>();
        if(head==null) {
            System.out.println("头结点不能为空");
            return ;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return ;
        }
        HeroNode tmp = head;
        while (tmp.getNext()!=null){
            stack.push(tmp.getNext());
            tmp=tmp.getNext();
        }
        while(!stack.isEmpty()){
            System.out.println(show(stack.pop()));
        }
    }
    //查找单链表中的倒数第k个结点
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回nulll
    public  HeroNode findLastIndexNode(int k){
        if(head==null) {
            System.out.println("头结点不能为空");
            return null;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return null;
        }
        if(k<0 ||k >getLength()){
            System.out.println("k节点不能小于0，或者大于链表节点数");
            return null;
        }
        HeroNode tmp = head.getNext();
        for (int i = 0; i < getLength()-k ; i++) {
            tmp  = tmp.getNext();
        }
        return  tmp;
    }
    //获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    public int getLength(){
        int linkedLength=0;
        if(head==null) {
            System.out.println("头结点不能为空");
            return -1;
        }
        if(head.getNext()==null){
            System.out.println("只有头结点，链表无数据");
            return 0;
        }
        HeroNode tmp = head;
        while (tmp.getNext()!=null){
            linkedLength++;
            tmp=tmp.getNext();
        }
        return  linkedLength;
    }
    public String show(HeroNode h){
        return "{" +
                "本节点id="+ h.toString().substring(h.toString().lastIndexOf('.')+1)+ '\'' +
                "id=" + h.getId() +
                ", name='" + h.getName() + '\'' +
                ", nickName='" + h.getNickName() + '\'' +
                ", next=" + h.getNext() + '\'' +
                '}'+"\n";
    }
}
class HeroNode{
    //值域
    private int id;
    private String name;
    private  String nickName;

    //指针域
    private HeroNode next;

    HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }
    /*    @Override
        public String toString() {
            return "HeroNode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", next=" + next +
                    '}'+"\n";
        }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }


}
