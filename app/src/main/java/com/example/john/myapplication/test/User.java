package com.example.john.myapplication.test;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by John on 2017/8/21.
 */
@Entity
public class User {
    @Id
    @Property(nameInDb="aa")
    private int id;
    private String name;
    private String age;
    private String sex;
    private String area;
    @Generated(hash = 1409033418)
    public User(int id, String name, String age, String sex, String area) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
