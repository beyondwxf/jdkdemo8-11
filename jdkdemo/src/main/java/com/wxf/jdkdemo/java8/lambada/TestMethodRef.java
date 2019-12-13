package com.wxf.jdkdemo.java8.lambada;

import com.wxf.jdkdemo.java8.lambada.been.Employee;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.PrintStream;
import java.time.Year;
import java.util.Comparator;
import java.util.function.*;

/**
 * @ClassName TestMethodRef
 * @Description
 * @Author wangxuefei
 * @Date 2019/12/6 11:23
 * @Version V1.0
 * 一、方法引用： 若Lambda体中的功能，已经有方法提供了实现，可以使用方法引用
 * （可以将方法引用理解为Lambda表达式的另外一种表达形式）
 * 1、对象的应用 :: 实例方法名
 * 2、类名 :: 静态方法名
 * 3、类名 :: 实例方法名
 * <p>
 * 注意：
 * 方法引用所引用的的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 * 若Lambda的参数列表的第一个参数，是实例方法的调用者，第二个参数（或无参）是实例方法的参数时，格式：ClassName :: MethodName
 **/
public class TestMethodRef {
    /**
     * 功能描述 数据引用
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/12/9
     */
    @Test
    public void testNew1() {
        Function<Integer, String[]> fun = (args) -> new String[args];
        String[] strs = fun.apply(10);
        System.out.println("--------------------");

        Function<Integer, Employee[]> fun2 = Employee[]::new;
        Employee[] emps = fun2.apply(20);
        System.out.println(emps.length);
    }

    /**
     * 功能描述 构造器引用
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/12/9
     */
    @Test
    public void testNew2() {
        Function<String, Employee> fun = Employee::new;
        BiFunction<String, Integer, Employee> fun2 = Employee::new;

    }

    @Test
    public void testNew3() {
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());

        System.out.println("--------------");

        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());
    }

    /**
     * 功能描述 类名：：实例方法名
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/12/9
     */
    @Test
    public void testNew4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        System.out.println(bp.test("aaaaaaaa", "sssssss"));

        System.out.println("------------------");

        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("abc", "abc"));
        System.out.println("---------------------");

        Function<Employee, String> fun = (e) -> e.show();
        System.out.println(fun.apply(new Employee()));

        Function<Employee, String> fun2 = Employee::show;
        System.out.println(fun2.apply(new Employee()));
    }

    /**
     * 功能描述 类名 ：： 静态方法名
     *
     * @author wangxuefei
     * @date 2019/12/9
     */
    @Test
    public void testNew5() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println("----------------------");

        Comparator<Integer> com2 = Integer::compare;
    }

    /**
     * 功能描述
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/12/9
     */
    @Test
    public void testNew6() {
        BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
        System.out.println(fun.apply(1.5, 22.2));
        System.out.println("---------------");

        BiFunction<Double, Double, Double> fun2 = Math::max;
        System.out.println(fun2.apply(1.2, 1.5));
    }


    /**
     * 功能描述 对象的引用 ：： 实例方法名
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/12/9
     */
    @Test
    public void testNew7() {
        Employee emp = new Employee(101, "zhangsan", 10, 2000.00);

        Supplier<String> sup = () -> emp.getName();
        System.out.println(sup.get());

        System.out.println("----------------------");

        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get());
    }


    @Test
    public void testNew8() {
        PrintStream ps = System.out;
        Consumer<String> con = (str) -> ps.println(str);
        con.accept("Hello World");

        System.out.println("----------------");

        Consumer<String> con2 = ps::println;
        con2.accept("Hello java8");

        Consumer<String> con3 = System.out:: println;
    }
}
