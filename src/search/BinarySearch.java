package search;

import java.util.*;

/**
 * @author Pengxinjie
 * @DATE 2020/3/9 - 16:10
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int temp = Binary(arr,0,arr.length-1,1234);
        System.out.println(temp);

        System.out.println();

        List<Integer> list = new ArrayList<>();
        int[] ar = {1,8,10,89,1000,1234,1234,6789};
        list = Binary_2(ar,0,ar.length-1,1234);

        for (Integer integer : list) {
            System.out.println(integer+" ");
        }
    }

    public static int Binary(int[] arr,int left,int right,int val){
        int mid = (left+right)/2;
        int midVal = arr[mid];

        if (right < left) {
            return -1;
        }

        if (midVal < val) {
            return Binary(arr, mid + 1, right, val);
        } else if (midVal > val) {
            return Binary(arr, left, mid - 1, val);
        } else {
            return mid;
        }
    }

    /**
     * 存在多个相同值
     * @param arr 要查询的数组
     * @param left 左索引
     * @param right 右索引
     * @param val 要查找的值
     * @return
     */
    public static ArrayList<Integer> Binary_2(int[] arr,int left,int right,int val) {
        int mid = (left+right)/2;
        int midVal = arr[mid];

        if (right < left) {
            return new ArrayList<>();
        }

        if (midVal < val) {
            return Binary_2(arr, mid + 1, right, val);
        } else if (midVal > val) {
            return Binary_2(arr, left, mid - 1, val);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(mid);
            //向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != val) {
                    break;
                }
                list.add(temp);
                temp -= 1;
            }
            //向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > right|| arr[temp] != val) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }
            return list;
        }
    }
}
