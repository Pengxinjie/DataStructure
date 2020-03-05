package array;

import org.junit.Test;

/**
 * @author Pengxinjie
 * @DATE 2020/3/4 - 13:21
 */
public class CircleRemoveArray {
    @Test
    public void test(){
        int[] a = {1,2,3,4,5,6,7};
        rotate(a,3);
        for (int i : a) {
            System.out.print(i+"    ");
        }
    }

    public void rotate(int[] nums, int k) {
        if (nums.length == 0 || nums.length == 1 || k % nums.length == 0) {
            return;
        }
        k %= nums.length;
        int length = nums.length;
        reverse(nums, 0, length - k - 1);
        reverse(nums, length - k, length - 1);
        reverse(nums, 0, length - 1);
    }

    private void reverse(int[] nums, int begin, int end) {
        for (int i = 0; i < (end - begin + 1) / 2; i++) {
            int temp = nums[begin + i];
            nums[begin + i] = nums[end - i];
            nums[end - i] = temp;
        }
    }
}

