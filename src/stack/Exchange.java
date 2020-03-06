package stack;

import com.sun.source.tree.DoWhileLoopTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Pengxinjie
 * @DATE 2020/3/6 - 20:23
 */
public class Exchange {
    public static void main(String[] args) {
        //1.示例：将 1+((2+3)*4)-5 转成  1 2 3 + 4 * + 5
        //2.由于直接对String进行操作不方便，因此将 1+((2+3)*4)-5转化成对应的List
        //3.将中缀表达式得到的list转为后缀表达式对应的list
        String expression = "1+((2+3)*4)-5";

        List<String> list = toInfixExpressionList(expression);
        List<String> parseSuffieExpression = parseSuffieExpression(list);
        System.out.println("后缀表达式对应的list是："+parseSuffieExpression);

        System.out.println("中缀表达式对应的list是："+list);

        System.out.println("计算的结果是："+PolandNotation.calculate(parseSuffieExpression));
    }

    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        //索引，指针，用于遍历String
        int i = 0;
        //str用来做多位数的拼接工作
        String str = "";
        //每遍历一个字符就放入c
        char c;
        do{
            //如果c是一个非数字,直接放入list
            if (( c = s.charAt(i) ) < 48 || ( c = s.charAt(i) ) > 57){
                list.add(""+c);
                i++;
            }else{
                //如果是个数，还得考虑存在多位数的情况
                //置空
                str = "";
                while (i < s.length() && ( c = s.charAt(i) ) >= 48 && ( c = s.charAt(i) ) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < s.length());
        return list;
    }

    public static List<String> parseSuffieExpression(List<String> ls){
        //定义两个栈:s1符号栈；s2是存储中间结果的栈
        Stack<String> s1 = new Stack<>();
        //由于s2整个过程都没有pop操作，而且我们还需要逆序输出，因此我们使用ArrayList
        ArrayList<String> s2 = new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数，入s2栈
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                //左括号入s1
                s1.push(item);
            }else if(item.equals(")")){
                while (!s1.peek().equals("(") ){
                    //还没找到“（”，就依次弹出s1栈顶的符号，并压入s2
                    s2.add(s1.pop());
                }
                //抛弃小括号
                s1.pop();
            }else{
                //当item的优先级小于等于s1栈顶的运算符，将s1栈顶的运算符弹出并加入s2中，再次判断此时如果s1为空或栈顶运算符为左括号，则将其直接入栈
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    //写一个判断优先级高低的方法
                    s2.add(s1.pop());
                }
                //将item压入栈
                s1.push(item);
            }
        }
        //把s1中剩下的运算符压入s2
        while (s1.size() > 0){
            s2.add(s1.pop());
        }
        return s2;
        //注意我们是存放在ArrayList中的，所以不需要逆序输出
    }
}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回优先级数字的方法
    public static int getValue(String operation){
        int result = 0;
        switch(operation){
            case "+": result = ADD;
                break;
            case "-": result = SUB;
                break;
            case "*": result = MUL;
                break;
            case "/": result = DIV;
                break;
             default:
                 System.out.println("不存在该运算符！");
        }
        return result;
    }
}