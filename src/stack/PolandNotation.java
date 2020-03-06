package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Pengxinjie
 * @DATE 2020/3/6 - 18:08
 * 逆波兰计算器的实现
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(3+4)*5-6的逆波兰表达式是： 3 4 + 5 * 6 -
        //为了使用方便，符号与数字用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路：先将表达式放入ArrayList中，然后将ArrayList传递给一个方法，配合栈完成计算

        List<String> list = getListString(suffixExpression);
        int res = calculate(list);
        System.out.println("计算的结果是:"+res);
    }
    /**
    *     将逆波兰表达式的数据和运算符依次放入ArrayList中
     */
    public static List<String> getListString(String suffixExpression){
        //先将suffixExpression分隔（" "）
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String s1 : split) {
            list.add(s1);
        }
        return list;
    }

    public static int calculate(List<String> list){
        //创建一个栈
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            //使用正则表达式取出数
            if (item.matches("\\d+")){
                //匹配多位数
                stack.push(item);
            }else{
                //如果不是数，那么弹出两个数计算然后入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("无效的运算符！");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
