package Array;

import Array.CircleArray;

import java.util.Scanner;

/**
 * @author Pengxinjie
 * @DATE 2020/2/4 - 16:05
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        CircleArray queueDemo=new CircleArray(3);
        char key=' ';//接收用户输入
        Scanner sc=new Scanner(System.in);
        boolean loop=true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取数据");
            System.out.println("h(head):查看队首的数据");
            key=sc.next().charAt(0);//接收一个字符
            switch(key){
                case 's':queueDemo.showQueue();
                    break;
                case 'e':
                    sc.close();
                    loop=false;
                    break;
                case 'a':
                    System.out.print("请输入要添加的值：");
                    int temp;
                    temp=sc.nextInt();
                    queueDemo.addValue(temp);
                    break;
                case 'g'://取出数据
                    try {
                        int res=queueDemo.getValue();
                        System.out.println("取出的数据是"+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': try {
                    int res=queueDemo.headValue();
                    System.out.println("队首数据为：" + res);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
                 default:
                     break;
            }
        }
        System.out.println("程序退出！");
    }
}

//使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;
    private int front;//队首
    private int rear;//队尾
    private int []arr;//模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=-1;//指向队头的前一个位置
        rear=-1;//指向队尾，刚开始没数据，则指向-1
    }

    //判断队列是否已满
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //判断队列是否为空
    public boolean isNull(){
        return rear==front;
    }
    //添加数据到队列
    public void addValue(int value){
        if (isFull()){
            System.out.println("队列已满！");
            return;
        }
        arr[++rear]=value;
    }
    //获取队列的数据（出队列）
    public int getValue(){
        if (isNull()) {
            //抛出异常
            throw new RuntimeException("队列空，不能取数据！");
        }
        return arr[++front];
    }
    //显示队列的所有数据(遍历数组)
    public void showQueue(){
        if (isNull()){
            System.out.println("队列为空！");
            return;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
    //显示队列的头数据
    public int headValue(){
        if (isNull()) {
            throw new RuntimeException("队列为空！");
        }
        else {
            return arr[front + 1];//注意front是指向队列头的前一个位置！
        }
    }
}