package com.wxf.jdkdemo.java8.lambada;

import com.wxf.jdkdemo.java8.lambada.been.Employee;

/**
 * @ClassName FilterEmployeeForAge 接口实现类
 * @Description
 * @Author wangxuefei
 * @Date 2019/11/25 16:32
 * @Version V1.0
 **/
public class FilterEmployeeForAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() <= 35;
    }
}
