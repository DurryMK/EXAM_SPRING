package com.des.client.mapper.system;

import com.des.client.entity.system.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id,mobile,username,birthday,pwd,email,qq,school,major,imgUrl,isRname,status,regTime from e_user where 1 = 1")
    public List<User> queryAllUser();

    @Select("select id,mobile,username,birthday,pwd,email,qq,school,major,imgUrl,isRname,status,regTime from e_user where 1 = 1 and mobile=#{mobile}")
    public User queryUserByMobile(String mobile);

    @Select("select id,mobile,username,birthday,pwd,email,qq,school,major,imgUrl,isRname,status,regTime from e_user where 1 = 1 and username=#{username}")
    public User queryUserByUsername(String username);

    @Select("select id,mobile,username,birthday,pwd,email,qq,school,major,imgUrl,isRname,status,regTime from e_user where 1 = 1 and email=#{email}")
    public User queryUserByEmail(String email);

    @Select("select id,mobile,username,birthday,pwd,email,qq,school,major,imgUrl,isRname,status,regTime from e_user where 1 = 1 and username=#{username} and pwd=#{pwd}")
    public User queryUserByPwd(String username, String pwd);

    @Insert("insert into e_user values(#{id},#{username},#{mobile},#{imgUrl},#{birthday},#{pwd},#{email},#{school},#{qq},#{major},#{isRname},#{status},#{regTime})")
    public void addUser(User user);
}
