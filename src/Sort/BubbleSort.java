package Sort;

/**
 * @author Pengxinjie
 * @DATE 2020/3/7 - 0:24
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a ={2,1,5,6,3,7};

        Bubble_2(a);
        for (int i : a) {
            System.out.print(i+"    ");
        }

        //测速
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100000);
        }
        System.out.println();

        long start = System.currentTimeMillis();
        Bubble_2(arr);
        long end = System.currentTimeMillis();

        double sum = (double) (end - start)/1000;

        for (int i : arr) {
            System.out.print(i+"  ");
        }
        System.out.println();
        System.out.println("共耗费"+sum+"秒");
        //Bubble_1:17.012秒
        //Bubble_2:16.78秒
    }

    //从前往后排
    public static void Bubble_1(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            //优化：如果某一次循环比较完，一次都没有交换，说明已经有序了，无需再继续比较
            boolean flag = false;
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]){
                    flag = true;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            if (!flag){
                break;
            }
        }
    }

    //从后往前排
    public static void Bubble_2(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j+1]){
                    flag = true;
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            if (!flag){
                break;
            }
        }
    }
}