package tree;

/**
 * @author Pengxinjie
 * @DATE 2020/3/10 - 21:23
 */
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        ThreadedNode root = new ThreadedNode(9,"苏亚雷斯");
        ThreadedNode t2 = new ThreadedNode(10,"梅西");
        ThreadedNode t3 = new ThreadedNode(11,"内马尔");
        ThreadedNode t4 = new ThreadedNode(12,"彭鑫杰");
        ThreadedNode t5 = new ThreadedNode(29,"郜林");
        ThreadedNode t6 = new ThreadedNode(18,"于汉超");

        root.setLeft(t2);
        root.setRight(t3);
        t2.setLeft(t4);
        t2.setRight(t5);
        t3.setLeft(t6);

        ThreadTree threadTree = new ThreadTree();
        threadTree.setRoot(root);
        threadTree.midOrder();

        System.out.println();
        //测试线索化
        threadTree.midThreadNodes(root);
        System.out.println(root.getLeft().getLeft().getRight());
        System.out.println(root.getLeft().getLeft().getRight().getRight().getRight().getRight().getLeft());

        //遍历
        threadTree.midThreadNodesOrder();
    }
}

class ThreadTree{
    private ThreadedNode root;
    //新增属性，为了实现线索化，需要创建一个指向当前结点的前驱结点指针
    private ThreadedNode pre;

    public void setRoot(ThreadedNode root) {
        this.root = root;
    }

    /**
     * 二叉树中序线索化
     * node就是当前需要线索化的结点
     */
    public void midThreadNodes(ThreadedNode node){
        //先判断
        if (node == null){
            return;
        }
        //开始中序线索化
        //1.线索化左子树
        midThreadNodes(node.getLeft());
        //2.线索化当前结点
        //先处理当前结点的前驱结点
        if (node.getLeft() == null){
            //让当前结点的左指针指向pre
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继结点,pre！=null防止第一个结点出现空指针异常
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个结点后让当前结点变为下一个结点的前驱结点
        pre = node;

        //3.线索化右子树
        midThreadNodes(node.getRight());
    }

    /**
     * 遍历中序线索化二叉树
     */
    public void midThreadNodesOrder () {
        ThreadedNode temp = root;
        while (temp != null){
            //循环找到leftType == 1的结点,随着遍历而变化，
            while (temp.getLeftType() == 0){
                temp = temp.getLeft();
            }
            System.out.println(temp);
            //如果当前结点的右指针是后继结点就一直输出
            while (temp.getRightType() == 1){
                //获取当前结点的后继结点
                temp = temp.getRight();
                System.out.println(temp);
            }
            temp = temp.getRight();
        }
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
    public ThreadedNode preOrderSearch(int value){
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
    public ThreadedNode midOrderSearch(int value){
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
    public ThreadedNode postOrderSearch(int value) {
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

class ThreadedNode{
    private int value;
    private String name;
    private ThreadedNode left;
    private ThreadedNode right;

    /**leftType==0表示指向左子树，leftType==1表示指向前驱结点
     *rightType同理
     */
    private int leftType;
    private int rightType;

    public ThreadedNode(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public ThreadedNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedNode left) {
        this.left = left;
    }

    public ThreadedNode getRight() {
        return right;
    }

    public void setRight(ThreadedNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ThreadNode{" +
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
    public ThreadedNode preOrderSearch(int value){
        //先比较当前结点
        if (this.value == value){
            return this;
        }
        ThreadedNode node = null;
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
    public ThreadedNode midOrderSearch(int value){
        ThreadedNode node = null;
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
    public ThreadedNode postOrderSearch(int value){
        ThreadedNode node = null;
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