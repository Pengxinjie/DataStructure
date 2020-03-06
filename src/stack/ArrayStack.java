package stack;

import java.util.Scanner;

/**
 * @author Pengxinjie
 * @DATE 2020/3/6 - 13:43
 * 栈的数组实现
 */
public class ArrayStack {
    public static void main(String[] args) {
        arraystackdemo a = new arraystackdemo(4);
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

/**
 * 定义一个类来表示栈
 */
class arraystackdemo {
    /**maxSize 表示栈的大小
     * stack 是用数组实现的栈
     * top 表示栈顶
     */
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public arraystackdemo(int maxSize) {
        this.maxSize = maxSize;
        //初始化数组
        stack = new int[maxSize];
    }

    /**
     * @return 栈是否已满
     */
    public boolean isFull(){
        return top == maxSize - 1;
    }

    /**
     * @return 栈是否为空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     * @param value 要入栈的值
     */
    public void push(int value){
        if (isFull()){
            System.out.println("栈已满，添加失败!");
            return;
        }
        stack[++top] = value;
    }

    /**
     * 出栈
     * @return 出栈的元素
     */
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，出栈失败！");
        }
        int vaule = stack[top--];
        return vaule;
    }

    /**
     * 遍历栈：从栈顶向下
     */
    public void printStack(){
        if(isEmpty()){
            System.out.println("栈空！");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println("stack["+i+"]:"+stack[i]+"    ");
        }
    }
}