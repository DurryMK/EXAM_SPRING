package com.des.client.mapper.system;

import com.des.client.entity.system.EToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TokenMapper {
    @Select("select * from e_token where name=#{name}")
    public EToken queryToken(String name);
}
