package Array;

import java.util.Scanner;

/**
 * @author Pengxinjie
 * @DATE 2020/2/4 - 18:11
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        //测试
        CircleArray queueDemo=new CircleArray(4);
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
class CircleArray {
    private int maxSize;
    private int front;//队首
    private int rear;//队尾
    private int []arr;//模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=0;//指向队列的第一个位置
        rear=0;//指向队尾的后一个位置
    }

    //判断队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
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
        arr[rear]=value;
        rear=(rear+1)%maxSize;//将rear后移
    }
    //获取队列的数据（出队列）
    public int getValue(){
        if (isNull()) {
            //抛出异常
            throw new RuntimeException("队列空，不能取数据！");
        }
        int temp=front;
        front=(front+1)%maxSize;
        return arr[temp];//front本身是指向队列的第一个元素
    }

    //求出当前队列有效数据的个数
    public int invaidValueNumber(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列的所有数据(遍历数组)
    public void showQueue(){
        if (isNull()){
            System.out.println("队列为空！");
            return;
        }
        //从front开始遍历
        for (int i = front; i <front+invaidValueNumber() ; i++) {
            System.out.println(arr[i%maxSize]);
        }
    }
    //显示队列的头数据
    public int headValue(){
        if (isNull()) {
            throw new RuntimeException("队列为空！");
        }
        else {
            return arr[front];//注意front是指向队头！
        }
    }
}