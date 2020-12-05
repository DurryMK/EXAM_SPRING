package com.des.client;

import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.system.User;
import com.des.client.mapper.question.QuestionPreMapper;
import com.des.client.mapper.system.UserMapper;
import com.des.client.utils.commonUtils.GenID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ClientApplicationTests {
    @Autowired
    private UserMapper um;

    @Autowired
    private QuestionPreMapper qp;
    @Test
    void contextLoads() {
        List<QuestionPre> leetCode = qp.queryQuestionPreByFrom("LeetCode");
        System.out.println(leetCode);
    }

}
