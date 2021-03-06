package linkedlist;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Pengxinjie
 * @DATE 2020/3/5 - 13:05
 */
public class studetList {
    public static void main(String[] args) {
        DoubLinkedList s = new DoubLinkedList();
        _Student s1 = new _Student("彭鑫杰",19);
        _Student s2 = new _Student("彭鑫",18);
        _Student s3 = new _Student("彭",17);

        //测试

        //增
        s.addValueTail(s1);
        s.addValueHead(s2);
        s.printList();
        System.out.println("----------------------------");

        //删
        s.deleteList(2);
        s.printList();
        System.out.println("------------------------------");

        //查
        System.out.println(s.getIndex(1).toString());
        System.out.println("-----------------------------");

        //改
        s.getIndex(1).setAge(20);
        System.out.println(s.getIndex(1).toString());
        System.out.println("------------------------------");

        System.out.println(s.getLength());
        s.addValue(s3,2);
        s.printList();
        System.out.println(s.isEmpty());

    }
    @Test
    public void test_1(){
        DoubLinkedList studentLinkedList = new DoubLinkedList();
        _Student s1 = new _Student("彭鑫杰",20);
        _Student s2 = new _Student("彭鑫",19);
        _Student s6 = new _Student("彭鑫",19);
        _Student s3 = new _Student("彭",21);
        _Student s4 = new _Student("杰",18);
        _Student s5 = new _Student("杰",18);

        studentLinkedList.addValuePlus(s1);
        studentLinkedList.addValuePlus(s3);
        studentLinkedList.addValuePlus(s6);
        studentLinkedList.addValuePlus(s5);
        studentLinkedList.addValuePlus(s2);
        studentLinkedList.addValuePlus(s4);

        System.out.println(studentLinkedList.FindLastIndexNode(studentLinkedList.getHeadNode(),6).toString());
    }

    @Test
    public void test(){
        DoubLinkedList s = new DoubLinkedList();
        _Student s1 = new _Student("彭鑫杰",20);
        _Student s2 = new _Student("彭鑫",19);
        _Student s6 = new _Student("彭鑫",19);
        _Student s3 = new _Student("彭",21);
        _Student s4 = new _Student("杰",18);
        _Student s5 = new _Student("杰",18);

        s.addValuePlus(s1);
        s.addValuePlus(s3);
        s.addValuePlus(s6);
        s.addValuePlus(s5);
        s.addValuePlus(s2);
        s.addValuePlus(s4);

        s.printList();

        System.out.println("---------------------------------------------");
        s.reversePrint(s.getHead());

        System.out.println("----------------------------------------------");

        s.reverseList();

        s.printList();
        System.out.println("---------------------");

        s.reverseList();
        s.printList();
    }
}

class Student {
    private String name;
    private int age;
    public _Student headNode;
    public _Student next;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
        //无参构造
    }

    @Override
    public String toString() {
        return "Studet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class StudentLinkedList{
    /**1.初始化头结点
     */
    _Student headNode = new _Student("",0);

    /**
     * @return 头结点
     */
    public _Student getHeadNode(){
        return headNode;
    }

    public boolean isEmpty(){
        return headNode.next == null;
    }

    /**
     * @param student 将student添加到链表尾部
     * */
    public void addValueTail(_Student student){
        _Student temp = headNode;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = student;
    }

    /**
     * @param student 将student添加到链表头部
     */
    public void addValueHead(_Student student){
        if (headNode.next == null){
            headNode.next = student;
            return;
        }
        student.next = headNode.next;
        headNode.next = student;
    }

    /**
     * @param student  要添加的结点
     * @param index    要添加的位置
     */
    public void addValue(_Student student, int index){
        if (index <= 0 || index > getLength()+1){
            System.out.println("index不存在！");
            return;
        }
        if(index == 1) {
            addValueHead(student);
            return;
        }
        if (index == getLength()+1){
            addValueTail(student);
            return;
        }

        _Student temp = getIndex(index-1);
        student.next = temp.next;
        temp.next = student;
    }

    /**
     * 按年龄顺序添加,从小到大
     * @param student 要添加的结点
     */
    public void addValuePlus(_Student student){
        _Student temp = headNode;
        if(headNode.next == null){
            headNode.next = student;
            return;
        }
        while (student.getAge() > temp.next.getAge()){
            temp = temp.next;
            if (temp.next == null){
                break;
            }
        }
        if(temp.next == null){
            temp.next = student;
            return;
        }
        student.next = temp.next;
        temp.next = student;
    }

    /**
     * 遍历链表
     */
    public void printList(){
        if(isEmpty()){
            System.out.println("链表空！");
            return;
        }
        _Student temp = headNode;
        while (temp.next != null){
            temp = temp.next;
            System.out.println(temp.toString());
        }
    }

    /**
     * @return 链表的长度
     */
    public int getLength(){
        if (isEmpty()){
            return 0;
        }
        int length = 0;
        _Student temp = headNode;
        while (temp.next != null){
            temp = temp.next;
            length++;
        }
        return length;
    }

    /**
     * @param index 要寻找的位置
     * @return 寻找到的结点
     */
    public _Student getIndex(int index){
        if(isEmpty()){
            throw new RuntimeException("链表空！index位置不存在！");
        }
        if (index <= 0 || index > getLength()){
            throw new RuntimeException("无效的位置！");
        }
        _Student temp = headNode;
        while (index-->0){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * @param index 删除index位置的结点
     */
    public void deleteList(int index){
        if(isEmpty()){
            System.out.println("链表空！删除失败");
            return;
        }
        if (index <= 0 || index > getLength()){
            System.out.println("无效的位置！");
            return;
        }
        //如果没有前一个位置
        if (index == 1) {
            headNode.next = headNode.next.next;
            return;
        }
        //寻找index的前一个元素
        _Student temp = getIndex(index-1);
        if (temp.next.next != null) {
            //删除index
            temp.next = temp.next.next;
        }else {
            temp.next = null;
        }
    }

    /**
     * 单链表反转
     */
    public void reverseList(){
        if (headNode.next == null || headNode.next.next ==null){
            return;
        }

        _Student temp = headNode.next;
        _Student next = null;
        //指向当前结点的下一个结点，防止链表断掉
        _Student reverseHead = new _Student("",0);

        //遍历原来的链表，一一取出，放在新链表的第一个位置
        while (temp != null){
            //先记住temp的下一个结点
            next = temp.next;
            //将temp的下一个结点指向新的链表的最前端
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            //temp后移
            temp = next;
        }

        headNode.next = reverseHead.next;
    }

    public _Student getHead(){
        return headNode;
    }

    //反向打印链表 -- 栈
    public void reversePrint(_Student head){
        if(isEmpty()){
            return;
        }
        if (head.next.next == null){
            return;
        }

        Stack<_Student> stack = new Stack<>();
        _Student temp = head.next;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
    //面试题：查找单链表的倒数第k个结点
    /**思路：
    1.编写一个方法接收头结点和index（index表示倒数index个结点）
    2.遍历链表得到链表的总长度size
    3.从链表的第一个开始遍历（size-index）个，便得到所需结点
    4.若找到返回该结点，没找到返回null
     */
    public _Student FindLastIndexNode(_Student student, int index){
        //判空
        if(student.next==null){
            System.out.println("该链表为空！");
            return null;
        }else{
            if(index<=0||index>getLength()){
                return null;
            }
            //从第一个开始
            _Student te=student.next;
            int temp=getLength()-index;
            while (temp-->0){
                te=te.next;
            }
            return te;
        }
    }
}