package com.wxf.jdkdemo.java8.lambada;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.wxf.jdkdemo.java8.lambada.fileTool.MyFun;
import org.junit.Test;


import java.util.*;
import java.util.function.Consumer;

/**
 * @ClassName TestLambda2
 * @Description
 * @Author wangxuefei
 * @Date 2019/11/29 14:54
 * @Version V1.0
 *
 * 一、Lambada 表达式的基础语法： java 中引入了一个新的操作符"->"该操作符称为箭头操作符或Lambda 操作符
 *              箭头操作符将Lambda 表达式拆分成两部分
 *     左侧：Lambda 表达式参数列表
 *     右侧：Lambda 表达式所需执行的功能，即Lambda体
 *
 *   语法格式一：无参数，无返回值
 *             () -> System.out.println("Hello Lambda")
 *   语法格式二：有一个参数，并且无返回值
 *             (x) -> System.out.println(x)
 *   语法格式三：若只有一个参数，小括号可以省略不写
 *             x -> System.out.println(x)
 *   语法格式四：有两个以上的参数，有返回值，并且 Lambda体重有多条语句
 *          Comparator<Inter> com = (x,y) -> {
 *              System.out.println("函数式接口");
 *              return Integer.compare(x,y);
 *          }
 *   语法格式五：若Lambda体重只有一条语句，return 和 大括号都可以省略不写
 *          Comparator<Integer> com (x,y) -> Integer.compare(x,y);
 *   语法格式六:Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即"类型推断"
 *           (Integer x,Integer y) -> Integer.compare(x,y);
 *
 *  上联：左右遇一括号省
 *  下联：左侧推断类型省
 *  横批：能省则省
 *
 *  二、Lambda 表达式需要"函数式接口"的支持
 *      函数式接口：接口中只能有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface 修饰
 *                可以检查是否函数式接口
 *
 *
 *
 *
 **/
public class TestLambda2 {
    @Test
    public void testOld1() {
        //jdk1.7之前必须是finnal
        int num = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hell world!" + num);
            }
        };
        runnable.run();
        System.out.println("============");

        Runnable runnable1 = ()->System.out.println("Hello Lambda!");
        runnable1.run();
    }


    @Test
    public  void testNew1(){
        Consumer<String> con = x -> System.out.println(x);
        con.accept("测试。。。。。。。");
    }

    @Test
    public void testNew2(){
        Comparator<Integer> com = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void testNew3(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void testNew4(){
//        String [] strs;
//        strs = {"aaa", "bbb", "ccc"};
        List<String> list = new ArrayList<>();
        show(new HashMap<>());
    }

    //需求：对一个数进行运算
    @Test
    public void  testNew5(){
        Integer num = operation(100,x->x*x);
        System.out.println(num);

        System.out.println(operation(200,y->y+100));
    }

    public  Integer operation(Integer num, MyFun myFun){
        return myFun.getValue(num);
    }

    public void show(Map<String, Integer> map){

    }
}
