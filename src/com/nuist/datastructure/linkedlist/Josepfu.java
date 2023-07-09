package com.nuist.datastructure.linkedlist;

/**
 * @author 陈志远
 * @version 1.0
 * @create 2023-07-04 23:49
 * @description
 */
public class Josepfu {
    public static void main(String[] args) {
//        测试一把，看看构建环形链表，和遍历是否正确
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个小孩结点
        circleSingleLinkedList.showBoy();
        System.out.println("-------------------------------------");
//        测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);//2→4→1→5→3
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //    创建一个first结点，当前没有编号
    private Boy first = new Boy(-1);

    //    添加小孩结点，构建成一个环形的链表
    public void addBoy(int nums) {//nums就是一共添加几个结点
//        nums做一个简单的数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
//         使用for创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
//            根据编号创建小孩结点
            Boy boy = new Boy(i);
//            如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //    遍历当前环形链表
    public void showBoy() {
//        判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
//        因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }
//    根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  从第几个小孩开始数的
     * @param countNum 代表你数几下
     * @param nums     表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
//        先对数据进行校验，至少要有些合理性
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误请重新输入");
            return;
        }
//        创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
//        1 需要创建一个辅助指针（变量）helper，事先应该指向环形链表的最后这个结点
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后小孩结点
                break;
            }
            helper = helper.getNext();
        }
//        小孩报数前，先让first和helper移动k-1次【k表示从第几个小孩开始数的】
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
//        当小孩报数时，让first和helper指针同时移动m-1次，然后出圈【m是报数的界值】
//        这里是一个循环操作，直到圈中只有一个结点
        while (true){
            if (helper == first){//说明圈中只有一个结点
                break;
            }
//          让first和helper指针同时移动countNum - 1
            for (int j = 0;j < countNum - 1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
//            这时first指向的结点，就是要出圈小孩的结点
            System.out.printf("小孩%d出圈\n",first.getNo());
//            这时将first指向的小孩结点出圈
            first = first.getNext();
            helper.setNext(first);//让helper的next指向first，不是helper指向first
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());//换成helper.getNo()一样
    }
}

//创建一个Boy类，表示一个结点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个结点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
