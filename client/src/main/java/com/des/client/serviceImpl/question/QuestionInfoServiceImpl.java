package com.des.client.serviceImpl.question;

import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.question.condition.QueCondition;
import com.des.client.mapper.question.QuestionInfoMapper;
import com.des.client.service.question.QuestionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class QuestionInfoServiceImpl implements QuestionInfoService {
    @Resource
    private QuestionInfoMapper preMapper;

    @Override
    public List<QuestionPre> getQuestionList(QueCondition condition) {
        //处理查询条件
        int startIndex = (condition.getCurrentPage() - 1) * condition.getPageSize();
        condition.setStart(startIndex);
        return preMapper.queryPreList(condition,QuestionPre.NO_DEL);
    }

    @Override
    public Integer getTotalWithCondition(QueCondition condition) {
        QueCondition newCondition = new QueCondition();
        newCondition.setStart(0);
        newCondition.setPageSize(Integer.MAX_VALUE);
        newCondition.setKey(condition.getKey());
        newCondition.setOwner(condition.getOwner());
        return preMapper.queryPreList(newCondition,QuestionPre.NO_DEL).size();
    }

    @Override
    public Integer getTotalWithUser(QueCondition condition) {
        QueCondition newCondition = new QueCondition();
        newCondition.setStart(0);
        newCondition.setPageSize(Integer.MAX_VALUE);
        newCondition.setOwner(condition.getOwner());
        return preMapper.queryPreList(newCondition,QuestionPre.NO_DEL).size();
    }
}
