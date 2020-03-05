package linkedlist;

import java.beans.EventHandler;
import java.nio.file.FileStore;

/**
 * @author Pengxinjie
 * @DATE 2020/3/5 - 21:01
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList c = new CircleSingleLinkedList();
        //加入5个结点
        c.addBoy(5);
        //遍历
        c.printList();

        //测试
        c.countBoy(1,2,5);// 2 4 1 5 3
    }
}

//创建一个结点类
class Boy{
    private int no;//编号
    private Boy next;//指向下一个结点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}

//创建一个环形单向链表
class CircleSingleLinkedList{
    //创建一个first结点，当前还没有编号
    private Boy first = null;

    public void addBoy(int nums){
        //参数校验
        if(nums < 1){
            System.out.println("nums值错误！");
            return;
        }
        //创建辅助指针
        Boy curBoy = null;

        for (int i = 1;i <= nums;i++){
            //根据编号创建结点
            Boy boy = new Boy(i);

            //如果是第一个
            if(i == 1){
                first = boy;
                //构成环
                boy.setNext(first);
                //first不能动，所以引进curBoy作为辅助指针
                curBoy = first;
            }else{
                //1.让curBoy的next指向新的结点
                curBoy.setNext(boy);
                //2.新boy指向first，构成环
                boy.setNext(first);
                //3.辅助指针curBoy前移
                curBoy = boy;
            }
        }
    }

    public void printList(){
        if (first == null){
            System.out.println("链表为空！");
            return;
        }
        //创建辅助指针
        Boy curBoy = first;
        while (true){
            System.out.println("编号为："+curBoy.getNo());
            //辅助指针后移
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出结点出圈的顺序
     * @param start 起点
     * @param countNum 一次数几下
     * @param nums 结点数
     */
    public void countBoy(int start,int countNum,int nums){
        //先校验数据
        if(first == null || start < 1 || start > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助指针，辅助删除结点
        Boy helper = first;
        //让辅助指针指向first的前一个结点
        while (helper.getNext() != first){
            helper = helper.getNext();
        }

        //让first和helper移到start位置,即移动start-1次
        for (int i = 0; i < start-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //开始报数，让first和helper指针分别移动countNum-1次，然后出圈
        while (helper != first){
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的结点就是要出圈的结点
            System.out.println("编号:"+first.getNo()+"出圈");
            //出圈代码
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后胜利者的编号是："+first.getNo());
    }

}