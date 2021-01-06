package com.des.client.service.componet.make;

import com.des.client.entity.paper.Invigilate;
import com.des.client.entity.paper.Paper;
import com.des.client.entity.system.Emap;
import org.springframework.stereotype.Service;

import java.util.Map;

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
     */
    Map savePaper(Paper paper, Emap em);

    /**
     * 更新第一步的试卷信息
     * 试卷实体类 操作结果
     */
    Map updatePaper(Paper paper, Emap em);

    /**
     * 更新第二步的属性设置
     * 属性实体类 试卷编码 创建者 操作结果
     */
    Invigilate updateMakSecond(Invigilate invigilates, String code, String owner, Emap em);
}
