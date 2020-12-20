package com.des.client.mapper.system;

import com.des.client.entity.system.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    User queryUser(User user);

    User queryUserByMobile(String mobile);

    String queryIDByMobile(String mobile);

    User queryUserByPwd(String username, String pwd);
}
