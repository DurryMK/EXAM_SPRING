package com.des.client;

import com.des.client.entity.paper.Paper;
import com.des.client.entity.paper.condition.PaperCondition;
import com.des.client.mapper.paper.PaperInfoMapper;
import com.des.client.mapper.paper.PaperMapper;
import com.des.client.utils.commonUtils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootTest
class ClientApplicationTests {
    @Resource
    private PaperMapper pm;
    @Resource
    private PaperInfoMapper pim;
    @Resource
    private RedisUtil rd;

    @Resource
    private PaperInfoMapper paperInfoMapper;
    @Test
    void contextLoads() throws UnsupportedEncodingException {
//        List<String> list = new ArrayList<String>();
//        list.add("asda");
//        rd.lRightPushAll("asdas",list);
        PaperCondition condition = new PaperCondition();
        condition.setOwner("1001");
        condition.setKey("1");
        condition.setStart(0);
        condition.setPageSize(10);
        List<Paper> paperList = paperInfoMapper.getPaperList(condition, Paper.NO_DEL);
        System.out.println(paperList);
    }

}
