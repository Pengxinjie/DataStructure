package Sort;

import linkedlist.Josepfu;

import java.time.temporal.Temporal;

/**
 * @author Pengxinjie
 * @DATE 2020/3/7 - 16:41
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] ar = {2,5,1,3,7,4};
        Shell_2(ar);
        for (int i : ar) {
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
        Shell_2(arr);
        long end = System.currentTimeMillis();

        //转化成秒
        double sum = (double) (end - start)/1000;

        for (int i : arr) {
            //System.out.println(i);
        }
        System.out.println();
        System.out.println("共耗费"+sum+"秒");
        //Shell_1:8.108秒
        //Shell_2:0.026秒
    }

    /**插入时使用交换（冒泡）法的Shell排序（效率低）
     * @param arr 要排序的数组
     */
    public static void Shell_1(int[] arr){
        int temp = 0;
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j-=gap) {
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    /**插入时使用移动法
     * @param arr 要排序的数组
     */
    public static void Shell_2(int[] arr){
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            //从gap元素开始，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
               int insertIndex = i;
               int insertVal = arr[i];
               if (arr[insertIndex] < arr[insertIndex-gap]){
                   while (insertIndex - gap >=0 && insertVal < arr[insertIndex-gap]){
                       //移动
                       arr[insertIndex] = arr[insertIndex - gap];
                       insertIndex -= gap;
                   }
                   arr[insertIndex] = insertVal;
               }
            }
        }
    }
}
