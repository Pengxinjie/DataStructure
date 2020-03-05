package linkedlist;

import javax.xml.transform.Source;
import java.io.DataOutputStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Pengxinjie
 * @DATE 2020/3/5 - 13:05
 */
public class studetList {
    public static void main(String[] args) {
        StudentLinkedList s = new StudentLinkedList();
        Student s1 = new Student("彭鑫杰",19);
        Student s2 = new Student("彭鑫",18);
        Student s3 = new Student("彭",17);

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
}

class Student {
    private String name;
    private int age;
    public Student headNode;
    public Student next;

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
    Student headNode = new Student("",0);

    /**
     * @return 头结点
     */
    public Student getHeadNode(){
        return headNode;
    }

    public boolean isEmpty(){
        return headNode.next == null;
    }

    /**
     * @param student 将student添加到链表尾部
     * */
    public void addValueTail(Student student){
        Student temp = headNode;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = student;
    }

    /**
     * @param student 将student添加到链表头部
     */
    public void addValueHead(Student student){
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
    public void addValue(Student student,int index){
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

        Student temp = getIndex(index-1);
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
        Student temp = headNode;
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
        Student temp = headNode;
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
    public Student getIndex(int index){
        if(isEmpty()){
            throw new RuntimeException("链表空！index位置不存在！");
        }
        if (index <= 0 || index > getLength()){
            throw new RuntimeException("无效的位置！");
        }
        Student temp = headNode;
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
        Student temp = getIndex(index-1);
        if (temp.next.next != null) {
            //删除index
            temp.next = temp.next.next;
        }else {
            temp.next = null;
        }
    }
}