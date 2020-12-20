package com.des.client.service.componet.make;

import com.des.client.entity.paper.PaperType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/20 10:37
 */
@Service
public interface MakeInfoService {
    List<PaperType> getTypeList(String owner);
}
