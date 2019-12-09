package com.wxf.jdkdemo.java8.lambada.fileTool;

import com.wxf.jdkdemo.java8.lambada.been.Employee;

/**
 * @ClassName FilterEmployeeForSalary
 * @Description
 * @Author wangxuefei
 * @Date 2019/11/25 16:42
 * @Version V1.0
 **/
public class FilterEmployeeForSalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary()>5000;
    }
}
