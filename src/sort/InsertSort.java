package sort;

/**
 * @author Pengxinjie
 * @DATE 2020/3/7 - 15:27
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {2,5,4,1,7};
        Insert(array);
        for (int i : array) {
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
        Insert(arr);
        long end = System.currentTimeMillis();

        //转化成秒
        double sum = (double) (end - start)/1000;

        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println();
        System.out.println("共耗费"+sum+"秒");
        //Insert:1.029秒
    }

    public static void Insert(int[] arr){
        //存放要插入的元素，否则后续移动元素时会被覆盖
        int insertVal = 0;
        //要插入的位置
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            //1.保证不越界；2.找到insertVal要插入的前一个位置
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if(insertIndex+1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
