package com.des.client.mapper.Login;

import com.des.client.entity.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id,mobile,username,pwd,email,qq from exam_user where 1 = 1")
    public List<User> queryAllUser();

    @Select("select id,mobile,username,pwd,email,qq from exam_user where 1 = 1 and mobile=#{mobile}")
    public User queryUserByMobile(String mobile);

    @Select("select id,mobile,username,pwd,email,qq from exam_user where 1 = 1 and username=#{username}")
    public User queryUserByUsername(String username);

    @Select("select id,mobile,username,pwd,email,qq from exam_user where 1 = 1 and email=#{email}")
    public User queryUserByEmail(String email);
}
