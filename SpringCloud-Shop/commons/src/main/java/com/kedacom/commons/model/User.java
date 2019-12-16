package com.kedacom.commons.model;

import java.io.Serializable;

/**
 * 实习期考核项目
 * com.kedacom.model
 * 2017-12-29-13:03
 * 2017科达科技股份有限公司-版权所有
 * Created by suxiongwei on 2017-12-29.
 */
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

    private String phone;

    private String address;

    public User() {
    }

    public User(String name, String password, String phone, String address) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
