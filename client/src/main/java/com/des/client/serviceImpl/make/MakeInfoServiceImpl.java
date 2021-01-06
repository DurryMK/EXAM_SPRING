package com.des.client.serviceImpl.make;

import com.des.client.entity.paper.PaperType;
import com.des.client.mapper.paper.PaperInfoMapper;
import com.des.client.service.componet.make.MakeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/20 10:38
 */
@Service
public class MakeInfoServiceImpl implements MakeInfoService {

    @Resource
    private PaperInfoMapper mapper;

    @Override
    public List<PaperType> getTypeList(String owner) {
        try {
            return mapper.getPaperTypeByUser(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
