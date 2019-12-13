package com.wxf.jdkdemo.java8.lambada.been;

/**
 * @ClassName Trader 交易员类
 * @Description
 * @Author wangxuefei
 * @Date 2019/12/12 17:08
 * @Version V1.0
 **/
public class Trader {
    private String name;
    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
