package tree;

import java.lang.invoke.VarHandle;

/**
 * @author Pengxinjie
 * @DATE 2020/3/10 - 10:47
 */
public class BinaryTree_1 {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = new Node(9,"苏亚雷斯");
        Node node2 = new Node(10,"梅西");
        Node node3 = new Node(11,"内马尔");
        Node node4 = new Node(12,"彭鑫杰");
        //先手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        //前序遍历
        tree.setRoot(root);
        tree.preOrder();
        System.out.println();
        //中序遍历
        tree.midOrder();
        System.out.println();
        //后序遍历
        tree.postOrder();

        System.out.println();
        //查找11号
        Node node = tree.postOrderSearch(10);
        System.out.println(node);
        System.out.println();

        //删除12号结点
        tree.delNode(12);
        tree.preOrder();
    }
}
 
class Node {
    private int value;
    private String name;
    private Node left;
    private Node right;

    public Node(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 二叉树的前序遍历
     */
    public void preOrder() {
        //先输出当前结点
        System.out.println(this);
        if (this.left != null) {
            //左递归
            this.left.preOrder();
        }
        if (this.right != null) {
            //右递归
            this.right.preOrder();
        }
    }

    /**
     * 二叉树的中序遍历
     */
    public void midOrder(){
        if (this.left != null) {
            //左递归
            this.left.midOrder();
        }
        //再输出当前结点
        System.out.println(this);
        if (this.right != null) {
            //右递归
            this.right.midOrder();
        }
    }

    /**
     * 二叉树的后序遍历
     */
    public void postOrder(){
        if (this.left != null) {
            //左递归
            this.left.postOrder();
        }
        if (this.right != null) {
            //右递归
            this.right.postOrder();
        }
        //再输出当前结点
        System.out.println(this);

    }

    /**前序查找
     * @param value 要查找的号码
     * @return 找到即返回结点，没找到返回null
     */
    public Node preOrderSearch(int value){
        //先比较当前结点
        if (this.value == value){
            return this;
        }
        Node node = null;
        //左递归
        if (this.left != null){
            node = this.left.preOrderSearch(value);
        }
        if (node != null){
            return node;
        }
        //右递归
        if (this.right != null){
            node = this.right.preOrderSearch(value);
        }
        if (node != null) {
            return node;
        }
        return node;
    }

    /**中序遍历查找
     * @param value 要找的值
     * @return 找到返回该结点，否则返回null
     */
    public Node midOrderSearch(int value){
        Node node = null;
        if (this.left != null){
            node = this.left.midOrderSearch(value);
        }
        if (node != null){
            return node;
        }
        if (this.value == value){
            return this;
        }
        if (this.right != null){
            node = this.right.midOrderSearch(value);
        }
        return node;
    }

    /**后续遍历查找
     * @param value 要查找的值
     * @return 找到返回该接结点，否则返回null
     */
    public Node postOrderSearch(int value){
        Node node = null;
        if (this.left != null){
            node = this.left.postOrderSearch(value);
        }
        if (node != null){
            return node;
        }
        if (this.right != null){
            node = this.right.postOrderSearch(value);
        }
        if (node != null){
            return node;
        }
        if (this.value == value){
            return this;
        }
        return node;
    }

    /**
     * 删除指定结点，如果是叶子结点就直接删除，如果是非叶子结点，就直接删除整颗子树
     * @param value 要删除的结点的值
     */
    public void delNode(int value){
        if (this.left != null && this.left.value == value){
            this.left = null;
            return;
        }
        if (this.right != null && this.right.value == value){
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.delNode(value);
        }
        if (this.right != null){
            this.right.delNode(value);
        }
    }
}

/**
 * 创建根结点
 */
class Tree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    /**前序遍历
     */
    public void preOrder(){
        if (this.root == null) {
            System.out.println("当前二叉树为空！");
            return;
        }
        root.preOrder();
    }

    /**中序遍历
     */
    public void midOrder(){
        if (this.root == null) {
            System.out.println("当前二叉树为空！");
            return;
        }
        root.midOrder();
    }

    /**后序遍历
     */
    public void postOrder(){
        if (this.root == null) {
            System.out.println("当前二叉树为空！");
            return;
        }
        root.postOrder();
    }

    /**
     * 前序遍历查找
     * @param value 要查找的值
     * @return 找到返回该结点，否则返回null
     */
    public Node preOrderSearch(int value){
        if(root != null){
            return root.preOrderSearch(value);
        }else{
            return null;
        }
    }

    /**
     * 中序遍历查找
     * @param value 要找的值
     * @return 找到则返回该结点，否则返回null
     */
    public Node midOrderSearch(int value){
        if (root != null){
            return root.midOrderSearch(value);
        }else{
            return null;
        }
    }

    /**
     * 后续遍历查找
     * @param value 要找的值
     * @return 找到则返回该结点，否则返回null
     */
    public Node postOrderSearch(int value) {
        if (root != null){
            return root.postOrderSearch(value);
        }else{
            return null;
        }
    }

    /**
     * 删除结点：如果是叶子结点直接删除，否则删除整颗子树
     * @param value
     */
    public void delNode(int value){
        if (root != null){
            //判断root是不是就是要删除的结点
            if (root.getValue() == value){
                root = null;
                return;
            }
            root.delNode(value);
        }else{
            System.out.println("空树无法删除！");
        }
    }
}