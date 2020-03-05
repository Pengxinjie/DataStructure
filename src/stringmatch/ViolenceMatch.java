package stringmatch;

/**
 * @author Pengxinjie
 * @DATE 2020/2/25 - 15:41
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        //测试暴力匹配算法
        String s1="asfhdsajklfjal";
        String s2="ajklf";

        int temp=violenceMatch(s1,s2);
        if(temp==-1){
            System.out.println("匹配失败");
        }else{
            System.out.println("匹配成功，下标为："+temp);
        }
    }
    //暴力匹配算法的实现
    public static int violenceMatch(String str1, String str2){
        char[] c1= str1.toCharArray();
        char[] c2= str2.toCharArray();
        int c1len = c1.length;
        int c2len = c2.length;

        //i指向c1，j指向c2
        int i = 0;
        int j = 0;

        //条件保证在匹配时不越界
        while (i<c1len && j<c2len){
            if (c1[i]==c2[j]){
                //匹配成功，继续匹配下一位
                i++;
                j++;
            }else{
                //i回到原来的位置并前移一位
                i = i-j+1;
                //j重新开始匹配
                j=0;
            }
        }

        if(j==c2len) {
            return i-j;
        }
        return -1;
    }
}
