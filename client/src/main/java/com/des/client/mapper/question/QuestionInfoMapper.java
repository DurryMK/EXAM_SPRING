package com.des.client.mapper.question;

import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.question.condition.QueCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionInfoMapper {
    /**
     *分页查询
     * */
    List<QuestionPre> queryPreList(@Param("condition") QueCondition condition,@Param("del")String del);
}
