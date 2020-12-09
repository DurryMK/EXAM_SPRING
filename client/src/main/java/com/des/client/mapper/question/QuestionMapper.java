package com.des.client.mapper.question;

import com.des.client.entity.question.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from e_question")
    public List<Question> queryAllQuestion();

    @Select("select * from e_question where id = #{id}")
    public Question queryQuestionById(@Param("id") String id);
}
