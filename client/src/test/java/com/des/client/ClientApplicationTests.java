package com.des.client;

import com.des.client.entity.paper.Invigilates;
import com.des.client.entity.question.QuestionComplete;
import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.question.condition.QueListQueryCondition;
import com.des.client.entity.system.User;
import com.des.client.mapper.make.PaperMapper;
import com.des.client.mapper.question.QuestionPreMapper;
import com.des.client.mapper.system.UserMapper;
import com.des.client.utils.commonUtils.GenID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.awt.print.Paper;
import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootTest
class ClientApplicationTests {
    @Resource
    private PaperMapper pm;
    @Test
    void contextLoads() throws UnsupportedEncodingException {
//        List<QuestionPre> questionListByPaper = pm.getQuestionListByPaper("11001");
//        System.out.println(questionListByPaper);
        System.out.println(pm.getPaperListByUser("1001"));;
    }

}
