package com.example.chen.myapplication.mvp.modle.bean;

/**
 * 时间: 2017/9/12 13:55
 * 作者: 陈宇杰
 * 作用:
 */

public class Bean {
    String name;
    String price;
    String store_name;
    int num;
    boolean is;

    public boolean is() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", store_name='" + store_name + '\'' +
                ", num=" + num +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
