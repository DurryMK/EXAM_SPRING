package com.des.client.mapper.make;

import com.des.client.entity.paper.PaperType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/19 20:28
 */
@Mapper
public interface PaperInfoMapper {
    List<PaperType> getPaperTypeByUser(@Param("owner")String owner);
}
