package com.des.client.entity.system;

import com.des.client.entity.base.BaseEntity;

public class User extends BaseEntity {

    private static final long serialVersionUID = 5054098176905250071L;
    private String id;
    private String mobile;
    private String username;
    private String pwd;
    private String email;
    private String qq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
