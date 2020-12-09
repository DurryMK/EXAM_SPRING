package com.des.client.mapper.question;

import com.des.client.entity.question.QuestionComplete;
import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.question.condition.QueListQueryCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionPreMapper {
    /**
     * 全量查询
     */
    @Select("select * from e_question_pre where id = #{id}")
    public QuestionPre queryQuestionPreById(@Param("id") String id);

    @Select("select * from e_question_pre e where e.from = #{from}")
    public List<QuestionPre> queryQuestionPreByFrom(@Param("from") String from);

    @Select("select * from e_question_pre where type = #{type}")
    public List<QuestionPre> queryQuestionPreByType(@Param("type") String type);

    @Select("select * from e_question_pre where type = #{level}")
    public List<QuestionPre> queryQuestionPreByLevel(@Param("level") String level);

    /**
     *分页查询
     * */
    public List<QuestionComplete> queryListByPage(QueListQueryCondition condition);
}
