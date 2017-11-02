package com.example.john.myapplication.test;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by jjtx-dev106 on 2017/8/21.
 */
@Entity
public class Teacher {
    @Id
    private int id;
    private String name;
    private boolean isMan;
    private String age;
    @Generated(hash = 1169430981)
    public Teacher(int id, String name, boolean isMan, String age) {
        this.id = id;
        this.name = name;
        this.isMan = isMan;
        this.age = age;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsMan() {
        return this.isMan;
    }
    public void setIsMan(boolean isMan) {
        this.isMan = isMan;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
