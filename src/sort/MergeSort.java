package sort;

/**
 * @author Pengxinjie
 * @DATE 2020/3/7 - 21:49
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] ar = {8,4,5,7,1,3,6,2};
        int[] temp = new int[ar.length];
        MergeSo(ar,0,ar.length-1,temp);
        for (int i : ar) {
            System.out.println(i+" ");
        }

        //测速
        int[] arr = new int[1000000];
        int[] temp2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*1000000);
        }
        System.out.println();

        long start = System.currentTimeMillis();
        MergeSo(arr,0,arr.length-1,temp2);
        long end = System.currentTimeMillis();

        //转化成秒
        double sum = (double) (end - start)/1000;
//
//        for (int i : arr) {
//            System.out.println(i);
//        }
        System.out.println();
        System.out.println("共耗费"+sum+"秒");
        //Mergeso:0.147秒    ---> 100 0000 的数据！
    }
    //分+和
    public static void MergeSo(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left+right)/2;

            //向左递归进行分解
            MergeSo(arr,left,mid,temp);
            //向右递归进行分解
            MergeSo(arr,mid+1,right,temp);

            //合并-->核心
            merge(arr,left,mid,right,temp);
        }
    }

    /**合并的方法
     * @param arr 要合并的数组
     * @param left 左边界（索引）
     * @param mid 中间索引
     * @param right 右边界（索引）
     * @param temp 中转数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        //初始化i，左边有序序列的初始索引
        int i = left;
        //初始化j，右边有序序列的初始索引
        int j = mid+1;
        //指向temp数组的当前索引
        int t = 0;

        //1.先把左右两边（有序）的数据按照规则填充到temp数组中，直到左右两边的有序序列，有一边处理完毕
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        //2.把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid){
            temp[t++] = arr[i++];
        }
        while (j <= right){
            temp[t++] = arr[j++];
        }
        t = 0;
        //3.将temp数组的元素拷贝到arr
        for (int k = left; k <= right; k++) {
            arr[k] = temp[t++];
        }
    }
}