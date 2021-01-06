package com.des.client.serviceImpl.make;

import com.des.client.consts.Sys;
import com.des.client.entity.paper.Invigilate;
import com.des.client.entity.paper.Paper;
import com.des.client.entity.system.Emap;
import com.des.client.mapper.paper.PaperMapper;
import com.des.client.service.componet.make.MakeService;
import com.des.client.utils.commonUtils.GenID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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
    public Map savePaper(Paper paper, Emap em) {
        try {
            //判断是否已有同名的试卷存在
            if (paperMapper.hasPaper(paper.getTitle(), paper.getOwner())>0) {
                return em.fail("已有同名试卷存在,保存试卷失败");
            }
            //创建试卷ID
            paper.setId(GenID.AID());
            //试卷状态 0草稿 1发布
            paper.setStatus(Paper.NO_EXPORT);
            //试卷是否删除 0未删除 1
            paper.setDel(Paper.NO_DEL);
            //试卷创建时间
            paper.setTime(Sys.DF());
            //保存试卷基本信息
            paperMapper.savePaper(paper);
            //创建对应试卷id的试卷设置
            paperMapper.saveInvigilate(new Invigilate(paper.getId()));
            return em.success("保存试卷成功");
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常");
        }
    }

    @Override
    public Map updatePaper(Paper paper,Emap em) {
        try {
            paperMapper.updatePaper(paper);
            return em.success("保存试卷成功");
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("保存试卷失败");
        }
    }

    @Override
    public Invigilate updateMakSecond(Invigilate invigilate,String code,String owner, Emap em) {
        try {
            //更新试卷属性
            paperMapper.updateInvigilate(invigilate,code,owner);
            return invigilate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
