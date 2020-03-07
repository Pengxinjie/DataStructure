package recursion;
/**
 * @author Pengxinjie
 * @DATE 2020/3/6 - 23:30
 */
public class EightQueen {
    //定义有多少个皇后
    int max = 8;
    //定义数组array,保存皇后放置的结果
    int[] array = new int[max];
    //方法的数量
    static int sum = 0;
    //判断的次数
    static int jugdeSum = 0;
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        //0表示放第一个皇后
        eightQueen.Check(0);

        System.out.println("共有"+EightQueen.sum+"种方法");
        System.out.println("共判断了"+EightQueen.jugdeSum+"次");
    }

    //将皇后摆放的位置打印出来
    private void print(){
        for (int i : array) {
            System.out.print(i+"  ");
        }
        System.out.println();
    }

    //编写一个方法，放置第n个皇后
    private void Check(int n){
        if (n == max){
            print();
            sum++;
            return;
        }else{
            //依次放入皇后，并判断是否冲突
            for (int i = 0; i < max; i++) {
                //先把当前这个皇后放在该行的第一列
               array[n] = i;
               //判断放置后是否冲突
                if (Judge(n)){
                    //不冲突->接着放 n+1
                    Check(n+1);
                }
            }
        }
    }

    //查看当我们放置n个皇后时，就去检测此皇后与 1 - (n-1)的皇后是否冲突
    /**
     * @param n  第n个皇后
     * @return
     */
    private boolean Judge(int n){
        jugdeSum++;
        for (int i = 0; i < n; i++) {
            if(array[i] == array[n]){
                //同一列
                return false;
            }else if(Math.abs(array[n]-array[i]) == n-i){
                //斜对面,构成一个正方形
                return false;
            }
        }
        return true;
    }
}