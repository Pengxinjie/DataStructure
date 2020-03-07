package Sort;

/**
 * @author Pengxinjie
 * @DATE 2020/3/7 - 15:05
 */
public class selectSort {
    public static void main(String[] args) {
        int[] a = {2,1,5,3,6};
        Select(a);
        for (int i : a) {
            System.out.print(i+" ");
        }
        System.out.println();
        //测速
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100000);
        }
        System.out.println();

        long start = System.currentTimeMillis();
        Select(arr);
        long end = System.currentTimeMillis();

        //转化成秒
        double sum = (double) (end - start)/1000;

        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println();
        System.out.println("共耗费"+sum+"秒");
        //Select:4.672秒
    }

    public static void Select(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            //假定最小的索引是i
           int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]){
                    min = j;
                }
            }
            //交换
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}
