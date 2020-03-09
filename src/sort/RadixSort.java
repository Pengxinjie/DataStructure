package sort;

/**
 * @author Pengxinjie
 * @DATE 2020/3/8 - 17:30
 * 基数排序（桶排序）-->空间换时间
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] ar = {3,1,5,2,4,6,8,7};
        Radix(ar);
        for (int i : ar) {
            System.out.print(i+" ");
        }

        //测速
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*10000000);
        }
        System.out.println();

        long start = System.currentTimeMillis();
        radix(arr);
        long end = System.currentTimeMillis();

        //转化成秒
        double sum = (double) (end - start)/1000;

        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println();
        System.out.println("共耗费"+sum+"秒");
        //Radix:0.793秒  -->1000 0000的数据
    }

    public static void radix(int[] arr){
        //先求出最大位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];

        int[] bucketIndex = new int[arr.length];
        int n = 1;
        while (maxLength-- > 0){
            for (int i = 0; i < arr.length; i++) {
                int wei = arr[i] / n % 10;
                bucket[wei][bucketIndex[wei]++] = arr[i];
            }
            n*=10;
            int index = 0;
            for (int i = 0; i < 10; i++) {
                if (bucketIndex[i] > 0){
                    for (int j = 0; j < bucketIndex[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                bucketIndex[i] = 0;
            }
        }
    }


    public static void Radix(int[] arr) {
        //先得得到数组中最大的数的位数
        //先找数组中最大的数vb
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //再得到最大数的位数(NB!)
        int maxLength = (max + "").length();

        //先搞10个桶
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录每个桶放入的数据的个数
        int[] bucketElementCount = new int[10];

        //辅助求出某数的某个位
        int n = 1;
        while (maxLength-- > 0) {
            for (int i = 0; i < arr.length; i++) {
                //取出每个元素的各个位
                int digitOfElement = arr[i] / n % 10;
                //放入对应桶
                bucket[digitOfElement][bucketElementCount[digitOfElement]++] = arr[i];
            }
            n *= 10;
            int index = 0;
            //按照这个桶的顺序，依次放入原数组
            for (int i = 0; i < 10; i++) {
                //如果桶中有数据，才放入原数组
                if (bucketElementCount[i] > 0) {
                    //循环遍历此桶
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        //取出数据放入arr，并让index后移
                        arr[index++] = bucket[i][j];
                    }
                    //清空桶
                    bucketElementCount[i] = 0;
                }
            }
        }
    }
}
