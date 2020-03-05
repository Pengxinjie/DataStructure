package linkedlist;
/**
 * @author Pengxinjie
 * @DATE 2020/2/5 - 14:56
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");
        HeroNode hero5=new HeroNode(0,"林冲","豹子");
        //创建链表
        SingleLinkedList listDemo=new SingleLinkedList();
        //加入
        listDemo.addPlus(hero2);
        listDemo.addPlus(hero1);
        listDemo.addPlus(hero4);
        listDemo.addPlus(hero3);//添加
        listDemo.showLinkedList();

        listDemo.update(hero5);//修改
        listDemo.showLinkedList();

        listDemo.DeleteNode(1);//删除
        System.out.println("该链表的结点个数为"+listDemo.getLength(listDemo.getHeadNode()));//获取结点个数
        listDemo.showLinkedList();
        System.out.println();
        System.out.println(listDemo.FindLastIndexNode(listDemo.getHeadNode(),2));

        listDemo.addPlus(hero1);
        //链表反转



    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;//英雄编号
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点
    public HeroNode(int n, String na, String nick){
        no=n;
        name=na;
        nickName=nick;
    }
    //为了显示方便，重写一下toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

//定义SinglinkedList来管理英雄
class SingleLinkedList{
    //1.初始化一个头结点
    private HeroNode HeadNode=new HeroNode(0,"","");//不存放具体数据，头结点不能改变，故使用private
    public HeroNode getHeadNode() {
        return HeadNode;
    }

    //2.添加结点
    //思路：找到当前链表的最后结点，将这个结点的next指向新的节点
    public void add(HeroNode hero){//无序添加
        //因为head结点不能动，所以我们需要一个辅助指针
        HeroNode temp=HeadNode;
        //遍历链表，找到next为null的结点(也就是最后一个节点)
        while (temp.next!=null){//next不为null的节点都不是最后一个节点
            temp=temp.next;//将temp后移
        }
        temp.next=hero;
    }
    public void addPlus(HeroNode hero){//考虑顺序的添加
        //创建辅助指针
        HeroNode temp=HeadNode;
        if(temp.next==null){
            temp.next=hero;
            return;
        }else{
            while(temp.next!=null){
                if(temp.next.no<hero.no)//循环结束时，temp的下一个结点的编号已经大于等于现在hero的编号了
                    temp=temp.next;
                else if(temp.next.no==hero.no){//排除等于的情况
                    System.out.println("添加失败！该结点的编号："+hero.no+"在原链表中已存在！");
                    return;
                }
                else
                    break;
            }
            //插入节点     temp （插入hero的位置）temp.next
            hero.next=temp.next;
            temp.next=hero;
        }

    }
    //3.遍历链表
    public void showLinkedList(){
        HeroNode temp=HeadNode.next;//temp为辅助指针
        if (temp==null){
            System.out.println("链表为空！");
            return;
        }
        do{
            System.out.println(temp);
            temp=temp.next;//后移
        }
        while (temp!=null);
    }
    //4.修改节点，根据no编号来改，且no本身不能改
    public void update(HeroNode hero){
        if (HeadNode.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=HeadNode.next;//第一个节点
        boolean flag =false;//标记，用于后续判断
        while (true){
            if(temp==null){
                break;//已遍历完该链表
            }
            if(temp.no==hero.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //利用flag是否修改过来判断是否找到要修改的结点
        if(flag){
            temp.nickName=hero.nickName;
            temp.name=hero.name;
        }else{//没找到
            System.out.println("无编号为:"+hero.no+"的节点");
        }
    }
    //5.删除结点
    public void DeleteNode(int no){//找到待删除结点的前一个结点
        HeroNode temp=HeadNode;
        boolean flag=false;//标记
        if (temp.next==null){//判空
            System.out.println("链表为空，无法删除！");
            return;
        }
        while (true){
            if(temp.next==null){//判断是否遍历完
                break;
            }
            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.next=temp.next.next;
            System.out.println("结点"+no+"已被删除！");
        }else{
            System.out.println("查无此节点！");
        }
    }
    //6.统计单链表的结点个数（不统计头结点）

    /**
     *
     * @param HeadNode 就是链表的头结点
     * @return 返回的链表结点的个数
     */
    public static int getLength(HeroNode HeadNode){
        if (HeadNode.next==null)
            return 0;
        else {
            int length=0;
            //定义一个辅助指针
            HeroNode temp=HeadNode.next;//去除头结点
            while (temp!=null){
                temp=temp.next;
                length++;
            }
            return length;
        }
    }

    //面试题：查找单链表的倒数第k个结点
    /*思路：
    1.编写一个方法接收头结点和index（index表示倒数index个结点）
    2.遍历链表得到链表的总长度size
    3.从链表的第一个开始遍历（size-index）个，便得到所需结点
    4.若找到返回该结点，没找到返回null
     */
    public static HeroNode FindLastIndexNode(HeroNode Headnode, int index){
        //判空
        if(Headnode.next==null){
            System.out.println("该链表为空！");
            return null;
        }else{
            if(index<=0||index>getLength(Headnode)){
                return null;
            }
            HeroNode te=Headnode.next;//从第一个开始
            int temp=getLength(Headnode)-index;
            while (temp-->0){
                te=te.next;
            }
            return te;
        }
    }
    //面试题：单链表反转
    /*思路：
    1.先定义一个节点reverseHead=new HeroNode
    2.从头到为遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表的最前端
    3.原来的链表的head.next=reverseHead.next
     */


}