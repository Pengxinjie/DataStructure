package array;
/**
 * @author Pengxinjie
 * @DATE 2020/2/3 - 20:49
 */
public class SpraseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数字 11*11
        //0：表示没有棋子;1：表示黑子;2：表示蓝子
        int chessArr1[][]=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        System.out.println("原数组为：");
        //遍历二维数组
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(chessArr1[i][j]+" ");
            }
            System.out.println();
        }
        //1.遍历二维数组得到非零数据的个数
        int sum=0;
        for (int[] x : chessArr1) {
            for (int i : x) {
                if(i!=0) {
                    sum++;
                }
            }
        }
        //2.创建稀疏数组
        int [][]sparseArr=new int[sum+1][3];
        //3.将数据存入稀疏数组
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        int temp=1;//记录是第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0){
                    sparseArr[temp][0]=i;
                    sparseArr[temp][1]=j;
                    sparseArr[temp][2]=chessArr1[i][j];
                    temp++;
                }
            }
        }
        //遍历稀疏数组
        System.out.println("得到的稀疏数组是：");
        for (int[] i : sparseArr) {
            for (int j : i) {
                System.out.print(j+" ");
            }
            System.out.println();
        }

        //将稀疏数组恢复成原始二维数组

        //1.先读取稀疏数组下标为0的一维数组，构建起原始二维数组的框架
        int [][]chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.依次赋值
        for (int i = 1; i < sparseArr.length; i++) {
                chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        //遍历原始二维数组
        for (int[] x : chessArr2) {
            for (int y : x) {
                System.out.print(y+" ");
            }
            System.out.println();
        }
    }
}