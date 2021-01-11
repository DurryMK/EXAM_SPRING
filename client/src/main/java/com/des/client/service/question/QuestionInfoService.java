package com.des.client.service.question;

import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.question.condition.QueCondition;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/6 17:47
 */
public interface QuestionInfoService {
    List<QuestionPre> getQuestionList(QueCondition condition);

    Integer getTotalWithCondition(QueCondition condition);

    Integer getTotalWithUser(QueCondition condition);
}
