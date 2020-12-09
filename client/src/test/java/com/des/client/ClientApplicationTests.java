package com.des.client;

import com.des.client.entity.question.QuestionComplete;
import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.question.condition.QueListQueryCondition;
import com.des.client.entity.system.User;
import com.des.client.mapper.question.QuestionPreMapper;
import com.des.client.mapper.system.UserMapper;
import com.des.client.utils.commonUtils.GenID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootTest
class ClientApplicationTests {
    @Autowired
    private UserMapper um;

    @Autowired
    private QuestionPreMapper qp;
    @Test
    void contextLoads() throws UnsupportedEncodingException {
        QueListQueryCondition condition = new QueListQueryCondition();
        String s = "À„∑®";
        System.out.println(s);
        System.out.println("À„∑®");
//        condition.setKey("∫⁄");
//        condition.setOwner("0");
//        condition.setLimit(10,20);
//        String[] level = {"1","2"};
//        String[] type = {"À„∑®"};
//        String[] time = {"2020-12-06"};
//        condition.setLevels(level);
//        condition.setTypes(type);
//        condition.setTimes(time);
//        System.out.println(condition);
//        List<QuestionComplete> questionCompletes = qp.queryListByPage(condition);
//        System.out.println(questionCompletes);
//        System.out.println(questionCompletes.size());
    }

}
