package com.vcredit.service.dto;

/**
 * 测试类
 * @Author chenyubin
 * @Date 2018/7/3
 */
public class DefaultChannelParam {
    private String name;
    private String descript;
    private int count = 0;//特意使用线程不安全属性，验证整体线程安全性


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
