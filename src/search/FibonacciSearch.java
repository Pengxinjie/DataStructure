package search;

import java.util.Arrays;

/**
 * @author Pengxinjie
 * @DATE 2020/3/9 - 20:50
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};

        int[] f = fib();
        for (int i : f) {
            System.out.println(i);
        }

        System.out.println();

        int a = FibSearch(arr,1234);
        System.out.println(a);
    }

    /**
     * 非递归得到斐波那契数列
     * @return 斐波那契数列
     */
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    /**非递归
     * @param arr 数组
     * @param key 查找的值
     * @return 找到则返回下标，否则返回-1
     */
    public static int FibSearch(int[] arr,int key){
        int low = 0;
        int hight = arr.length-1;
        //表示斐波那契分割数值的下标
        int k = 0;
        int mid = 0;
        //获取斐波那契数列
        int[] f = fib();
        //获取斐波那契分割数值的下标
        while (hight > f[k]-1){
            k++;
        }
        //因为f[k]可能大于数组的长度，因此我们需要扩容原数组
        int[] temp = Arrays.copyOf(arr,f[k]);
        //扩容带来的默认的0，用arr数组最后的数来填充
        for (int i = hight+1; i < temp.length; i++) {
            temp[i] = arr[hight];
        }

        while (low <= hight){
            mid = low + f[k-1] - 1;
            if (key < temp[mid]){
                //向左查找
                hight = mid-1;
                //继续拆分,即在f[k-1]的前面找
                k--;
            }else if (key > temp[mid]){
                //向右查找
                low = mid+1;
                // f[k] = f[k-1](左) + f[k-2](右)
                k -= 2;
            }else{
                if (mid <= hight){
                    return mid;
                }else{
                    return hight;
                }
            }
        }
        return -1;
    }
}
