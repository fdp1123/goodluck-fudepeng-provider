package com.bw.fudepeng.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fudepeng on 2017/7/28.
 * 用户实体类
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    private String sex;
    @Column(name = "photo")
    private String photo;
    @Column(name = "hobby")
    private String hobby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
