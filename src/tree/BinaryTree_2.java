package tree;

/**
 * @author Pengxinjie
 * @DATE 2020/3/10 - 14:54
 * 数组存储二叉树
 */
public class BinaryTree_2 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**完成顺序存储二叉树的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index) {
        //如果数组为空，或者arr.length==0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树前序遍历");
            return;
        }
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    public void preOrder(){
        this.preOrder(0);
    }
}