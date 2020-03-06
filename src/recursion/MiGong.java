package recursion;

import javax.xml.stream.FactoryConfigurationError;
import java.awt.geom.FlatteningPathIterator;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Pengxinjie
 * @DATE 2020/3/6 - 22:06
 * 迷宫回溯问题
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组来模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙，先把上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //再把左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        for (int i = 0; i < 3; i++) {
            map[3][i] = 1;
        }
        //遍历当前地图
        System.out.println("当前地图：");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+"  ");
            }
            System.out.println();
        }

        //使用递归回溯来给小球找路
        setWay(map,1,1);
        //遍历后来地图
        System.out.println("后来地图：");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt+"  ");
            }
            System.out.println();
        }
    }

    /**
     * @param map 地图
     * @param i 开始的行号
     * @param j 开始的列号
     * @return 是否找到,是：ture，否则返回：false
     */
    public static boolean setWay(int [][] map,int i,int j){
        //如果小球能到map[6][5]位置，则说明找到
        //map[i][j] = 0表示没有走过；=1表示墙；=2表示通路，可以走；=3表示走过，但走不通
        //确定一个策略：下->右->上->左,如果该点走不通就回溯
        if (map[4][1] == 2){
            return true;
        }else{
            if(map[i][j] == 0){
                //如果还没有走过,按策略走
                //假定这点可以走通
                map[i][j] = 2;

                if(setWay(map,i+1,j)){
                    //向下走
                    return true;
                }else if (setWay(map,i,j+1)){
                    //向右走
                    return true;
                }else if(setWay(map,i-1,j)){
                    //向上走
                    return true;
                }else if(setWay(map,i,j-1)){
                    //向左走
                    return true;
                }else{
                    //哪都走不通
                    map[i][j] = 3;
                    return false;
                }
            }else {//可能是 1(墙） 2（已走过，不要重复走了） 3（走不通）
                return false;
            }
        }
    }
}
