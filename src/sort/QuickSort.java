package sort;

/**
 * @author Pengxinjie
 * @DATE 2020/3/7 - 19:42
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] ar = {5, 1, 2, 3, 6, 4};
        Quick_1(ar,0,ar.length-1);
        for (int i : ar) {
            System.out.print(i + " ");
        }
        System.out.println();
        //测速
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*10000000);
        }
        System.out.println();

        long start = System.currentTimeMillis();
        Quick_2(arr,0,arr.length-1);
        long end = System.currentTimeMillis();

        //转化成秒
        double sum = (double) (end - start)/1000;
//
//        for (int i : arr) {
//            System.out.println(i);
//        }
        System.out.println();
        System.out.println("共耗费"+sum+"秒");
        //Quick:1.326秒    ---> 1000 0000 的数据！
    }

    public static void Quick_1(int[] arr, int low, int high) {
        //中间的下标值
        int pivot;
        if (low < high){
            pivot = Partition_1(arr,low,high);
            //对比中间数小的数递归
            Quick_1(arr,low,pivot-1);
            //对比中间数大的数递归
            Quick_1(arr,pivot+1,high);
        }
    }

    public static void Quick_2(int[] arr, int low, int high) {
        //中间的下标值
        int pivot;
        while (low < high){
            pivot = Partition_1(arr,low,high);
            //对比中间数小的数递归
            Quick_1(arr,low,pivot-1);
            low = pivot+1;
        }
    }

    private static int Partition_1(int[] arr, int low, int high) {
        //使用第一个数作为中间值
        int pivotKey = arr[low];

        while (low < high){
            while (low < high && arr[high] >= pivotKey){
                high--;
            }
            swap(arr,high,low);

            while (low < high && arr[low] <= pivotKey){
                low++;
            }
            //交换low和high
            swap(arr,low,high);
        }
        return low;
    }

    /** 优化：碰到较为有序的数据的优化-->选取合适的中间值
     */
    private static int Partition_2(int[] arr, int low, int high) {
        int pivotKey;
        //找到一个合适的数作为中间值  选择该序列中的最左端和最右端即中间的三个数的中间值
        int min = low + (high - low)/2;
        //保证左端的值最小
        if (arr[low] > arr[high]){
            swap(arr,low,high);
        }
        if (arr[min] > arr[high]){
            swap(arr,min,high);
        }
        if (arr[min] > arr[low]){
            swap(arr,min,low);
        }
        pivotKey = arr[low];

        while (low < high){
            while (low < high && arr[high] >= pivotKey){
                high--;
            }
            swap(arr,high,low);

            while (low < high && arr[low] <= pivotKey){
                low++;
            }
            //交换low和high
            swap(arr,low,high);
        }
        return low;
    }

    /** 再优化：交换数据--->替换数据
     */
    private static int Partition_3(int[] arr, int low, int high) {
        int pivotKey;
        //找到一个合适的数作为中间值  选择该序列中的最左端和最右端即中间的三个数的中间值
        int min = low + (high - low)/2;
        //保证左端的值最小
        if (arr[low] > arr[high]){
            swap(arr,low,high);
        }
        if (arr[min] > arr[high]){
            swap(arr,min,high);
        }
        if (arr[min] > arr[low]){
            swap(arr,min,low);
        }
        pivotKey = arr[low];

        //暂存中间值
        int temp = pivotKey;

        while (low < high){
            while (low < high && arr[high] >= pivotKey){
                high--;
            }
            //替换
            arr[low] = arr[high];

            while (low < high && arr[low] <= pivotKey){
                low++;
            }
            //替换
            arr[high] = arr[low];
        }
        //中间值回归
        arr[low] = temp;
        return low;
    }

    private static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }
}