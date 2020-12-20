package com.des.client.mapper.make;

import com.des.client.entity.paper.Invigilates;
import com.des.client.entity.paper.Paper;
import com.des.client.entity.question.Question;
import com.des.client.entity.question.QuestionPre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/15 19:40
 */
@Mapper
public interface PaperMapper {
    /**
     * 查询用户名下的试卷列表
     * */
    List<Paper> getPaperListByUser(@Param("owner") String owner);

    /**
     * 查询单张试卷的题目列表
     * */
    List<QuestionPre> getQuestionListByPaper(@Param("paperId")String paperId);

    /**
     * 查询单张试卷的监考属性
     * */
    List<Invigilates> findPaperInvigilates(@Param("invid")String invid);

    /**
     * 查询用户是否已经有同名试卷
     * */
    Integer hasPaper(@Param("title")String title,@Param("owner")String owner);

    /**
     * 保存试卷的基本信息
     * */
    Integer savePaper(Paper paper);

    /**
     * 更新试卷的基本信息
     * */
    void updatePaper(Paper paper);
}
