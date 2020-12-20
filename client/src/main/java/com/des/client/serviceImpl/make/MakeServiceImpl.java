package com.des.client.serviceImpl.make;

import com.des.client.consts.Sys;
import com.des.client.entity.paper.PConst;
import com.des.client.entity.paper.Paper;
import com.des.client.mapper.make.PaperMapper;
import com.des.client.service.componet.make.MakeService;
import com.des.client.utils.commonUtils.GenID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.*;
import java.util.UUID;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/15 14:57
 */
@Service
public class MakeServiceImpl implements MakeService {

    @Resource
    private PaperMapper paperMapper;


    @Override
    public Paper saveMakeFirst(Paper paper) {
        try {
            //判断是否已有同名的试卷存在
            if (paperMapper.hasPaper(paper.getTitle(), paper.getOwner())>0) {
                return null;
            }
            //创建试卷ID
            paper.setId(GenID.AID());
            //试卷状态 0草稿 1发布
            paper.setStatus(PConst.NO_EXPORT);
            //试卷是否删除 0未删除 1
            paper.setDel(PConst.NO_DEL);
            //试卷创建时间
            paper.setTime(Sys.DF());
            paperMapper.savePaper(paper);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return paper;
    }

    @Override
    public Paper updateMakeFirst(Paper paper) {
        try {
            paperMapper.updatePaper(paper);
            return paper;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
