package com.wxf.jdkdemo.java8.lambada;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName TestLambda3
 * @Description
 * @Author wangxuefei
 * @Date 2019/12/2 17:14
 * @Version V1.0
 *
 * java8 内置的四大核心函数接口
 *
 * Consumer<T> ： 消费型接口
 *      void accept(T t);
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 * Fuction<T> : 函数型接口
 *      R apply(T t);
 *
 * Predicate<T> : 断言型接口
 *      boolean test(T t):
 *
 **/
public class TestLambda3 {
    /**
     *功能描述 Predicate <T> 断言型接口
     * @author wangxuefei
     * @date 2019/12/2
     * @param
     * @return void
     */
    @Test
    public void TestNew1(){
        List<String> list = Arrays.asList("hello", "sdasda", "Lambda", "www", "ok");
        List<String> stringList = filterStr(list, s -> s.length() > 3);
        for (String str : stringList) {
            System.out.println(str);
        }
    }



    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if(pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

    /**
     *功能描述 Function<T,R>
     * @author wangxuefei
     * @date 2019/12/4
     * @param
     * @return void
     */
    @Test
    public void  testNew2(){
        String newStr = strHandler("\t\t\t 我测试数据啊啊啊啊啊 ",str->str.trim());
        System.out.println(newStr);

        String subStr = strHandler("我是测试啊啊啊啊啊", str -> str.substring(2, 5));
        System.out.println(subStr);
    }

    public String strHandler(String str, Function<String,String> function) {
        return function.apply(str);
    }

    /**
     *功能描述 Supplier<T> 供给型接口
     * @author wangxuefei
     * @date 2019/12/4
     * @param
     * @return void
     */
    @Test
    public void  testNew3(){
        List<Integer> numList = getNumList(10,()->(int)(Math.random()*100));
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return  list;
    }

    /**
     *功能描述 Consumer<T> 消费型接口
     * @author wangxuefei
     * @date 2019/12/6
     * @param
     * @return void
     */
    @Test
    public void testNew4() {
        happy(1000,(m)->System.out.println("我就是试试，这玩意好用吗，带入参数"+m+"元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }



}
