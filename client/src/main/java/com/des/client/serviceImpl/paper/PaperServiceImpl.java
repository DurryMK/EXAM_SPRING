package com.des.client.serviceImpl.paper;

import com.des.client.entity.paper.Paper;
import com.des.client.entity.paper.condition.PaperCondition;
import com.des.client.mapper.paper.PaperInfoMapper;
import com.des.client.service.componet.paper.PaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/29 10:35
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    private PaperInfoMapper infoMapper;

    @Override
    public List<Paper> getPaperList(PaperCondition condition) {
        try {
            List<Paper> returnList = new ArrayList<>();
            //处理查询条件
            int startIndex = (condition.getCurrentPage() - 1) * condition.getPageSize();
            condition.setStart(startIndex);
            List<Paper> paperList = infoMapper.getPaperList(condition, Paper.NO_DEL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = new Date();
            Date end = new Date();
            Date current = new Date();
            //处理查询结果
            for (Paper paper : paperList) {
                start = dateFormat.parse(paper.getStart());
                end = dateFormat.parse(paper.getEnd());
                if (current.compareTo(start) < 0) {
                    paper.setRunningStatus("-1");
                } else {
                    if (current.compareTo(start) > 0 && current.compareTo(end) < 0) {
                        paper.setRunningStatus("0");
                    } else {
                        paper.setRunningStatus("1");
                    }
                }
                returnList.add(paper);
            }
            return returnList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
