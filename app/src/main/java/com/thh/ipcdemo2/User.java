package com.thh.ipcdemo2;

import java.io.Serializable;

/**
 * Created by TangHui on 2015/10/12.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    public static int userId = 1000;

    public String name;
    public int age;
    public boolean isMan;

    public User(String name, int age, boolean isMan) {
        this.name = name;
        this.age = age;
        this.isMan = isMan;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMan=" + isMan +
                '}';
    }
}
