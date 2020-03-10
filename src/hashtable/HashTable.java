package hashtable;

/**
 * @author Pengxinjie
 * @DATE 2020/3/10 - 9:05
 */
public class HashTable {
    public static void main(String[] args) {
        //创建一个hash表
        HashTab hashTab = new HashTab(7);
        //实例化球员
        Footballer f1 = new Footballer(10,"梅西");
        Footballer f2 = new Footballer(9,"苏亚雷斯");
        Footballer f3 = new Footballer(11,"内马尔");
        //添加到hash表中
        hashTab.add(f1);
        hashTab.add(f2);
        hashTab.add(f3);
        //遍历hash表
        hashTab.printList();
        //寻找球衣号码为11的球员
        hashTab.findFootballer(11);
    }
}

/**
 * 球员类
 */
class Footballer {
    public int num;
    public String name;
    public Footballer next;

    public Footballer(int num, String name) {
        this.num = num;
        this.name = name;
    }

    @Override
    public String toString() {
        return "-->  Footballer{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 球员链表
 */
class FootballerList{
    /**头指针指向第一个元素，无头结点
     */
    private Footballer head = null;

    /**添加 ->尾添加
     * @param fo 球员
     */
    public void add(Footballer fo){
        //如果是添加第一个雇员
        if (head == null){
            head = fo;
            return;
        }
        Footballer temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = fo;
    }

    public void printList(int i){
        if (head == null){
            System.out.println("第"+i+"条链表为空！");
            return;
        }
        System.out.println("第"+i+"条链表信息为：");
        Footballer temp = head;
        while (temp != null){
            System.out.println(temp.toString());
            temp = temp.next;
        }
        System.out.println();
    }

    public Footballer find(int num){
        if (head == null){
            return null;
        }
        Footballer temp = head;
        while (temp != null){
            if (temp.num == num){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}

class HashTab{
    private FootballerList[] listArray;
    //表示共有多少条链表
    private int size;
    public HashTab(int size){
        this.size = size;
        //初始化链表
        listArray = new FootballerList[size];
        for (int i = 0; i < size; i++) {
            listArray[i] = new FootballerList();
        }
    }

    public void add(Footballer fo){
        //根据球员的num，从得到该员工应该添加到哪条链表里
        int foNum = hashFun(fo.num);
        listArray[foNum].add(fo);
    }

    /**
     * 散列函数
     * @param num num值
     * @return 散列值
     */
    public int hashFun(int num){
        return num % size;
    }

    public void printList(){
        //遍历哈希表
        for (int i = 0; i < size; i++) {
            listArray[i].printList(i+1);
        }
    }

    /**
     * 根据num找到球员
     * @param num 球员球衣号码
     * @return 球员
     */
    public void findFootballer(int num){
        int foNum = hashFun(num);
        Footballer fo = listArray[foNum].find(num);
        if (fo == null){
            System.out.println("未找到该球员");
            return;
        }else{
            System.out.println("在第"+(foNum+1)+"条链表找到该球员："+fo.toString());
        }
    }
}