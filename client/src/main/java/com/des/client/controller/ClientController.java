package com.des.client.controller;

import com.des.client.conf.ResConst;
import com.des.client.entity.system.User;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import com.des.client.utils.commonUtils.AesCodeUtil;
import com.des.client.utils.commonUtils.RedisUtil;
import com.des.client.utils.SMSUtils.SMSUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController()
@RequestMapping("/app")
public class ClientController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommonServiceImpl commonService;
    @Autowired
    private LoginServiceImpl loginService;

    private final int VCODE_TIME = 3000; //验证码过期时间 单位（分钟）

    @RequestMapping("/initPage")
    public Map initPage(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            User user = (User)request.getSession().getAttribute(ResConst.USERLOGINTOKEN);
            if(null == user){
                resultMap.put(ResConst.RESTOKEN, ResConst.FAIL);
                resultMap.put(ResConst.RESINFO, "未登录");
                return resultMap;
            }
            resultMap.put(ResConst.RESTOKEN, ResConst.SUCCESS);
            resultMap.put(ResConst.RESINFO, "已登录");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(ResConst.RESTOKEN, ResConst.FAIL);
            resultMap.put(ResConst.RESINFO, "系统异常");
        }
        return resultMap;
    }

}
