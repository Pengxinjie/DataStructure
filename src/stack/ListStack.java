package stack;

import java.util.Scanner;

/**
 * @author Pengxinjie
 * @DATE 2020/3/6 - 14:23
 * 栈的链表实现
 */
public class ListStack {
    public static void main(String[] args) {
        ListStackDemo a = new ListStackDemo();
        String key = "";
        //loop控制是否退出循环
        boolean loop = true;
        Scanner sc = new Scanner(System.in);

        while (loop){
            System.out.println("show:显示栈");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("exit:退出栈");

            System.out.print("请输入你的选择：");
            key = sc.next();

            switch(key){
                case "show":
                    a.printStack();
                    break;
                case "push":
                    int value;
                    System.out.print("请输入您要添加的值：");
                    value = sc.nextInt();
                    a.push(value);
                    break;
                case "pop":
                    try {
                        int temp = a.pop();
                        System.out.println("出栈的元素是："+temp);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    //资源关闭
                    sc.close();
                    break;
                default:
                    System.out.println("输入无效！");
            }
            System.out.println("--------------------------------------");
        }
        System.out.println("程序已退出！");
    }
}

class StackNode {
    private int value;
    public StackNode next;

    public StackNode(int value) {
        this.value = value;
    }

    public StackNode(int value, StackNode next) {
        this.value = value;
        this.next = next;
    }

    public StackNode() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class ListStackDemo{
    /**
     * HeadNode:初始化头结点
     */
    private StackNode Head = new StackNode(0,null);

    /**
     * @return 栈是否为空
     */
    public boolean isEmpty (){
        return Head.next == null;
    }

    /**
     * push数据到栈中
     * @param value 要push的数据
     */
    public void push(int value){
        //创建一个新节点装入数据
        StackNode Node = new StackNode(value);
        if(Head.next == null){
            Head.next = Node;
            Node.next = null;
            return;
        }
        Node.next = Head.next;
        Head.next = Node;
    }

    /**
     * pop数据
     * @return 返回已经pop的数据
     */
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，出栈失败！");
        }
        int value = Head.next.getValue();
        Head.next = Head.next.next;
        return value;
    }

    public void printStack(){
        if(isEmpty()){
            System.out.println("栈空！");
            return;
        }
        //创建辅助指针
        StackNode temp = Head.next;
        while (temp != null){
            System.out.println(temp.getValue());
            temp = temp.next;
        }
    }
}