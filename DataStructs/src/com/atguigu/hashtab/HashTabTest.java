package com.atguigu.hashtab;

import org.junit.Test;


/**
 * @author z
 * @createdate 2019-08-15 9:35
 */
public class HashTabTest {
    @Test
    public void test() {
        HashTab ht = new HashTab(5);
        ht.add(new Hero(1, "宋江"));
        ht.add(new Hero(2, "吴用"));
        ht.add(new Hero(3, "林冲"));
        ht.add(new Hero(4, "武松"));
        ht.add(new Hero(5, "武大郎"));
        ht.add(new Hero(6, "鲁智深"));
        ht.add(new Hero(7, "关胜"));
        ht.add(new Hero(8, "玉麒麟"));
        ht.list();
        ht.findHeroById(7);
    }
}

class HashTab {
    private HeroLinked[] heroLinkeds;
    //表示有多少条链表
    private int size;

    public HashTab(int size) {
        this.size = size;
        heroLinkeds = new HeroLinked[size];
        for (int i = 0; i < size; i++) {
            //要分别初始化每个链表
            heroLinkeds[i] = new HeroLinked();
        }
    }

    public void add(Hero e) {
        int i = hashFun(e.id);
        heroLinkeds[i].addNewNode(e);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            heroLinkeds[i].printLinked();
        }
    }

    public void findHeroById(int id) {
        int i = hashFun(id);
        Hero hero = heroLinkeds[i].findEmpById(id);
        System.out.println("第" + (i + 1) + "条" + hero);
    }

    private int hashFun(int id) {
        return id % size;
    }


}

class Hero {
    int id;
    String name;
    Hero next;

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

class HeroLinked {
    //头指针，执行第一个Emp,因此我们这个链表的head 是直接指向第一个Emp
    private Hero head = null;

    public void addNewNode(Hero h) {
        if (head == null) {
            head = h;
            return;
        }
        Hero mmp = head;
        while (mmp.next != null) {
            mmp = mmp.next;
        }
        mmp.next = h;
    }

    public void printLinked() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Hero mmp = head;
        System.out.println(mmp);
//        while (true) {
//            System.out.println(mmp);
//            if (mmp.next == null)
//                break;
//            mmp = mmp.next;
//        }
    }

    public Hero findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Hero mmp = head;
        while (true) {
            if (mmp.id == id)
                return mmp;
            if (mmp.next == null)
                break;
            mmp = mmp.next;
        }
        return null;
    }
}