package stringmatch;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Pengxinjie
 * @DATE 2020/2/25 - 16:02
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
    }

    //1.获取(一个字符串)子串的部分匹配值
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        //下标为0的位置匹配值肯定为0
        next[0]=0;

        for (int i = 1,j = 0; i < dest.length() ; i++) {
            while (j>0&&dest.charAt(i)!=dest.charAt(j)){
                j = next[j-1];
            }
            //当这个条件满足时，部分匹配值+1
            if (dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }

        return next;
    }
    
}
