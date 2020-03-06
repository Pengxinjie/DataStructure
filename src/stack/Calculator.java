package stack;

import java.util.Scanner;
/**
 * @author Pengxinjie
 * @DATE 2020/3/6 - 15:29
 *
 * 栈实现简易计算器（中缀表达式）
 */
public class Calculator {
    public static void main(String[] args) {
        //要被扫描的表达式
        String expression = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入一个表达式：");
        expression = sc.next();

        //创建两个栈:s1为数栈；s2位符号栈
        calStack numStack = new calStack(10);
        calStack operStack = new calStack(10);

        //定义需要用到的相关变量
        //index:遍历表达式的索引
        int index = 0;
        int num1 = 0,
            num2 = 0,
            oper = 0,
            res = 0;
        //将每次扫描到的char保存到ch中去
         char ch = ' ';
         //处理多位的整数
        String keepNum = "";

        //(扫描表达式)依次得到expression的每一个值
         while (true){
             ch = expression.substring(index,index+1).charAt(0);
             //判断ch是什么，然后做相应的操作
             if (operStack.isOper(ch)){
                 if (operStack.isEmpty()){
                     //直接入栈
                     operStack.push(ch);
                 }else{
                     //判断优先级
                     //小于等于直接pop出两个数和一个符号运算
                     if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                         num2 = numStack.pop();
                         num1 = numStack.pop();
                         oper = operStack.pop();
                         res = operStack.cal(num1,num2,oper);
                         //运算结果入数栈
                         numStack.push(res);
                         //当前符号入符号栈
                         operStack.push(ch);
                     }else{
                         //大于，直接放入符号栈中
                         operStack.push(ch);
                     }
                 }
             }
             else {
                 keepNum += ch;
                 //判断ch是否是字符串的最后一个字符
                 if(index == expression.length()-1){
                     numStack.push(Integer.parseInt(keepNum));
                 }else{
                     if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                         numStack.push(Integer.parseInt(keepNum));
                         keepNum = "";
                     }
                 }
             }
             //让index++并判断是否到表达式的最后了
             index++;
             if (index == expression.length()){
                 break;
             }
         }

         //扫描完表达式后，顺序的从数栈和符号栈中pop出相应的符号和数，运算后放入数栈，直到数栈只剩下一个元素
        while (true){
            //符号栈为空则已经计算出最后的结果，数栈中只有一个数字（结果）
            if (operStack.isEmpty()){
                break;
            }else{
                num2 = numStack.pop();
                num1 = numStack.pop();
                oper = operStack.pop();
                res = operStack.cal(num1,num2,oper);
                numStack.push(res);
            }
        }
        System.out.println("计算结果为："+numStack.pop());
    }
}

/**
 * 定义一个类来表示数栈
 */
class calStack {
    /**maxSize 表示栈的大小
     * stack 是用数组实现的栈
     * top 表示栈顶
     */
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public calStack(int maxSize) {
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

    /**
     * @param oper 操作符
     * @return 操作符对应的数字，数字越大优先级越高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '-' || oper == '+'){
            return 0;
        }else{
            //假定目前只有加减乘除，不含括号
            return -1;
        }
    }

    /**
     * @param val 输入的符号
     * @return 判断符号是否是运算符
     */
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算两个数
     * @param num1 前面的数
     * @param num2 后面的数
     * @param oper 运算符
     * @return 运算结果
     */
    public int cal(int num1,int num2,int oper){
        if(oper == '*'){
            return num1 * num2;
        }else if(oper == '-'){
            return num1 - num2;
        }else if(oper == '/'){
            return num1/num2;
        }else if(oper == '+'){
            return num1 + num2;
        }else{
            throw new RuntimeException("无效的运算符！");
        }
    }

    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("栈空，获取栈顶元素失败！");
        }
        return stack[top];
    }
}