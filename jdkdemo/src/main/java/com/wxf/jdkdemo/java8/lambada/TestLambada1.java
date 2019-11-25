package com.wxf.jdkdemo.java8.lambada;

import com.wxf.jdkdemo.JdkdemoApplication;
import com.wxf.jdkdemo.java8.lambada.been.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

/**
 * @ClassName TestLambada1
 * @Description
 * @Author wangxuefei
 * @Date 2019/11/22 17:35
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JdkdemoApplication.class)
public class TestLambada1 {

    /**
     * 功能描述  老的写法
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/11/25
     */
    @Test
    public void testOld1() {
        Comparator<String> com = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        TreeSet<String> ts = new TreeSet<>(com);
        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
    }

    /**
     * 功能描述 现在Lambda表达式的写法
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/11/25
     */
    @Test
    public void testNew1() {
        Comparator<String> com = (x, y) -> Integer.compare(x.length(), y.length());
        TreeSet<String> ts = new TreeSet<>(com);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 999.99),
            new Employee(102, "李四", 20, 666.66),
            new Employee(103, "王五", 30, 333.33),
            new Employee(104, "赵六", 77, 7777.77),
            new Employee(105, "田七", 29, 9999.999)
    );

    /**
     * 功能描述 获取公司中年龄小于35岁的员工信息
     *
     * @param emps
     * @return java.util.List<com.wxf.jdkdemo.java8.lambada.been.Employee>
     * @author wangxuefei
     * @date 2019/11/25
     */
    public List<Employee> filterEmployeeAge(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() <= 35) {
                list.add(emp);
            }
        }
        return list;
    }

    /**
     * 功能描述 老的过滤方法
     * 获取公司中年龄小于35岁的员工信息
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/11/25
     */
    @Test
    public void testOld2() {
        List<Employee> list = filterEmployeeAge(emps);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 功能描述 需求：获取公司中工资大于5000的员工信息
     *
     * @param emps
     * @return java.util.List<com.wxf.jdkdemo.java8.lambada.been.Employee>
     * @author wangxuefei
     * @date 2019/11/25
     */
    public List<Employee> filterEmployee(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getSalary() >= 5000) {
                list.add(emp);
            }
        }
        return list;
    }

    /**
     * 功能描述 优化一 策略设计模式
     *
     * @param emps
     * @param mp
     * @return java.util.List<com.wxf.jdkdemo.java8.lambada.been.Employee>
     * @author wangxuefei
     * @date 2019/11/25
     */
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> mp) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : emps) {
            if (mp.test(employee)) {
                System.out.println(employee);
            }
        }
        return list;
    }


    /**
     * 功能描述 优化一   老方法 通过传接口动态代理（策略模式实现）
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/11/25
     */
    @Test
    public void testOld3() {
        List<Employee> list = filterEmployee(emps, new FilterEmployeeForAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("--------------------------------------------");

        List<Employee> list2 = filterEmployee(emps, new FilterEmployeeForSalary());
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }

    /**
     * 功能描述 优化方式二：匿名内部类
     *
     * @param
     * @return
     * @author wangxuefei
     * @date 2019/11/25
     */
    @Test
    public void test4() {
        List<Employee> list = filterEmployee(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 5000;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 功能描述  优化方式三 lambda表达式
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/11/25
     */
    @Test
    public void getTestNew3() {
        List<Employee> list = filterEmployee(emps, (e) -> e.getAge() <= 35);

        list.forEach(System.out::println);
        System.out.println("-------------------------");
        List<Employee> list2 = filterEmployee(emps, (e) -> e.getSalary() > 5000);
        list2.forEach(System.out::println);
    }

    /**
     * 功能描述 优化方式四
     *
     * @param
     * @return void
     * @author wangxuefei
     * @date 2019/11/25
     */
    @Test
    public void testNew4() {
        emps.stream()
                .filter((e) -> e.getAge() <= 35)
                .forEach(System.out::println);
        System.out.println("--------------------------");

        emps.stream()
                .map(Employee::getName)
                .limit(3)
                .sorted()
                .forEach(System.out::println);
    }

}
