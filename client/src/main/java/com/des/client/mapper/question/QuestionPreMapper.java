package com.des.client.mapper.question;

import com.des.client.entity.question.QuestionPre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionPreMapper {
    @Select("select * from e_question_pre where id = #{id}")
    public QuestionPre queryQuestionPreById(@Param("id") String id);

    @Select("select * from e_question_pre e where e.from = #{from}")
    public List<QuestionPre> queryQuestionPreByFrom(@Param("from") String from);

    @Select("select * from e_question_pre where type = #{type}")
    public List<QuestionPre> queryQuestionPreByType(@Param("type") String type);

    @Select("select * from e_question_pre where type = #{level}")
    public List<QuestionPre> queryQuestionPreByLevel(@Param("level") String level);
}
