package search;

/**
 * @author Pengxinjie
 * @DATE 2020/3/9 - 20:27
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        int temp = InsertValue(arr,0,arr.length-1,3);
        System.out.println(temp);
    }

    public static int InsertValue(int[] arr,int left,int right,int val){
        if (left > right || val < arr[0] || val > arr[arr.length-1]){
            return -1;
        }
        int mid = left + (right - left)*(val - arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];

        if (val > midVal){
            //右递归
            return InsertValue(arr,left+1,right,val);
        }else if(val < midVal){
            //左递归
            return InsertValue(arr,left,right-1,val);
        }else{
            return mid;
        }
    }
}
