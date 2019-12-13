package com.wxf.jdkdemo.java8.lambada;

import com.wxf.jdkdemo.java8.lambada.been.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName TestStreamAPI
 * @Description
 * @Author wangxuefei
 * @Date 2019/12/10 15:14
 * @Version V1.0
 * <p>
 * 一、Stream Api 的操作步骤
 * 1、创建Stream
 * 2、中间操作
 * 3、终止操作（终端操作）
 **/
public class TestStreamAPI {
    /**
     * 功能描述  创建Stream
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/12/10
     */
    @Test
    public void test1() {
        //1、Collection 提供了两个方法 Stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        //获取一个顺序流
        Stream<String> stream = list.stream();
        //获取一个并行流
        Stream<String> parllelStream = list.parallelStream();

        //2、通过Arrays中的Stream()获取一个数据流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3、通过Stream类中静态方法of（）
        Stream<Integer> stream2 = Stream.of(1, 2, 7, 3, 4, 5, 6);

        //4、创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }

    //2、中间操作
    List<Employee> emps = Arrays.asList(
            new Employee(102, "lisi", 59, 666.66),
            new Employee(102, "张三", 18, 999.99),
            new Employee(102, "王五", 40, 800.66),
            new Employee(102, "赵六", 20, 700.66),
            new Employee(102, "田七", 32, 333.66)
    );

/**
 *
 * 筛选与切片
 * filter----接收Lambda,从流中排除某些元素
 * limit----截断流,使其元素不超过给定数量
 * skip(n)------跳过元素，返回一个扔掉前n个元素的流，若流中元素不足n个，则返回一个空流
 *              与limit(n)互补
 * distinct----筛选，通过流所生成元素的hashCode() 和 equals() 去除重复元素
 */

    /**
     * 功能描述 内部迭代：迭代操作 Stream Api 内部完成
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/12/12
     */
    @Test
    public void test2() {
        //所有的中间操作不会做任何的处理
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("测试中间操作");
                    return e.getAge() > 20;
                });
    }

    //外部迭代
    @Test
    public void test3() {
        Iterator<Employee> it = emps.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void  test4(){
        emps.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() > 800.00;
                }).limit(3)
                .forEach(System.out::println);
    }

    @Test
    public  void  test5(){
        emps.parallelStream()
                .filter(e->e.getSalary()>600)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public  void  test6() {
        emps.stream()
                .distinct()
                .forEach(System.out::println);
    }
    /**
     *给定一个数字列表，如何返回一个由么个数的平方构成的列表
     *  给定【1，2，3，4，5】 应该返回【1,4,9,16,25】
     */
    @Test
    public void  test7() {
        Integer[] nums = new Integer[]{1,2,3,4,5};
        Arrays.stream(nums)
                .map(x->x*x)
                .forEach(System.out::println);
    }

    /**
     *功能描述 怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
     */
    @Test
    public void test8() {
        Optional<Integer> count = emps.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(count.get());
    }

}
