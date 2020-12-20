package com.des.client.controller.system;

import com.des.client.service.system.CommonService;
import com.des.client.utils.commonUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/16 7:37
 */
public abstract class AbstractController {
    @Autowired
    protected CommonService commonService;
    @Autowired
    protected RedisUtil redisUtil;
}
