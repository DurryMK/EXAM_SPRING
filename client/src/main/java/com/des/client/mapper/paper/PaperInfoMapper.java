package com.des.client.mapper.paper;

import com.des.client.entity.paper.Paper;
import com.des.client.entity.paper.PaperType;
import com.des.client.entity.paper.condition.PaperCondition;
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

    Paper getPaperByCode(@Param("code")String code);

    List<Paper> getPaperList(@Param("condition") PaperCondition condition, @Param("del")String del);
}
