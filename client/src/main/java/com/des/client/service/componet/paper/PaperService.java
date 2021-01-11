package com.des.client.service.componet.paper;

import com.des.client.entity.paper.Paper;
import com.des.client.entity.paper.condition.PaperCondition;
import com.des.client.entity.system.PageCondition;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/29 10:36
 */
@Service
public interface PaperService {
    List<Paper> getPaperList(PaperCondition paperCondition) throws ParseException;

    Integer getTotalWithCondition(PaperCondition paperCondition);
}
