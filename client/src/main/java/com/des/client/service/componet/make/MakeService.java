package com.des.client.service.componet.make;

import com.des.client.entity.paper.Paper;
import org.springframework.stereotype.Service;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/16 7:35
 */
@Service
public interface MakeService {
    /**
     * 创建试卷的第一步步骤
     * 保存试卷的基本信息
     * */
    Paper saveMakeFirst(Paper paper);

    /**
     * 更新第一步的试卷信息
     * */
    Paper updateMakeFirst(Paper paper);
}
