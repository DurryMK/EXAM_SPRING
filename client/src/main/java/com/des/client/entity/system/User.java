package com.des.client.entity.system;

import com.des.client.entity.base.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class User extends BaseEntity {

    private static final long serialVersionUID = 5054098176905250071L;
    private String id;//ID
    private String mobile;//手机号
    private String username;//用户名
    private String birthday;//年龄
    private String email;//邮箱
    private String qq;//QQ
    private String isRname;//是否实名
    private String school;//就读学校
    private String imgUrl;//头像链接
    private String status;//账号状态
    private String RegTime;//注册时间
    private String major;//专业

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getIsRname() {
        return isRname;
    }

    public void setIsRname(String isRname) {
        this.isRname = isRname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegTime() {
        return RegTime;
    }

    public void setRegTime(String regTime) {
        RegTime = regTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", isRname='" + isRname + '\'' +
                ", school='" + school + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                ", RegTime='" + RegTime + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
