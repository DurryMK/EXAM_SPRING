package com.des.client.serviceImpl.question;

import com.des.client.consts.Res;
import com.des.client.entity.ListInPage;
import com.des.client.entity.question.QuestionComplete;
import com.des.client.entity.question.condition.QueListQueryCondition;
import com.des.client.mapper.question.QuestionMapper;
import com.des.client.mapper.question.QuestionPreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl {
    @Autowired
    private QuestionPreMapper preMapper;

    @Autowired
    private QuestionMapper queMapper;

    /**
     * 查询题目列表
     */
    public Map queryQuestionListInfoByPage(QueListQueryCondition condition, ListInPage listInPage) {
        Map map = new HashMap<String, Object>();
        try {
            //1.设置查找的范围
            int start = listInPage.getCurrentPage() * listInPage.getPageSize();
            condition.setLimit(start, listInPage.getPageSize());
            //2.设置查找的条件
            //2.1 关键字
            condition.setKey(listInPage.getSearchKey());
            //2.2 类型
            condition.setTypes((String[])listInPage.getTypes());
            //2.3 创建时间
            condition.setTimes((String[])listInPage.getCreateTimes());
            //2.4 难度
            condition.setLevels((String[])listInPage.getLevels());
            //3.调用接口查询当页数据
            List<QuestionComplete> questionCompletes = preMapper.queryListByPage(condition);
            //4.查询该类型数据的总条数
            int total = queryTotalByCondition(condition);
            //5.查询 当前页 该类型数据所包含的所有类型、难度等级、创建时间
            Map<String, Object> property = queryPropertyByList(questionCompletes);
            //6.以上所有数据集成到ListInPage中
            listInPage.setTotal(total);
            listInPage.setInfos(questionCompletes);
            listInPage.setLevels(property.get("levels"));
            listInPage.setCreateTimes(property.get("times"));
            listInPage.setTypes(property.get("types"));
            //返回
            map.put(Res.RESTOKEN, Res.SUCCESS);
            map.put(Res.RESINFO, listInPage);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Res.RESTOKEN, Res.FAIL);
            map.put(Res.RESINFO, "获取数据失败");
        }
        return map;
    }

    /**
     * 获取一个题目列表中所包含的类型，时间，难度等级
     */
    private Map<String, Object> queryPropertyByList(List<QuestionComplete> questionCompletes) {
        Map map = new HashMap<String, Object>();
        Set types = new HashSet<String>();
        Set times = new HashSet<String>();
        Set levels = new HashSet<String>();
        for (QuestionComplete question : questionCompletes) {
            types.add(question.getType());
            times.add(question.getTime());
            levels.add(question.getLevel());
        }
        map.put("types", types);
        map.put("times", times);
        map.put("levels", levels);
        return map;
    }

    public int queryTotalByCondition(QueListQueryCondition condition) {
        try {
            //取消数量限制
            condition.setLimit(0);
            List<QuestionComplete> questionCompletes = preMapper.queryListByPage(condition);
            //获取查询数据的数量
            return questionCompletes.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
