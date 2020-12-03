package com.des.client.controller;

import com.des.client.conf.ResConst;
import com.des.client.entity.system.User;
import com.des.client.mapper.Login.UserMapper;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import com.des.client.utils.commonUtils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller()
@RequestMapping("/access")
public class LoginController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommonServiceImpl commonService;
    @Autowired
    private LoginServiceImpl loginService;
    @Resource
    private UserMapper um;

    @RequestMapping("/quickLogin")
    public @ResponseBody
    Map initPage(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        String mobile = request.getParameter("mobile");
        String vcode = request.getParameter("vcode");
        try {
            //快速登录
            loginService.quickLogin(mobile, vcode, resultMap, request);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put(ResConst.RESTOKEN, ResConst.FAIL);
            resultMap.put(ResConst.RESINFO, e);
        }
        return resultMap;
    }

    @RequestMapping("/test")
    public @ResponseBody
    void test(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        try {
            User user = um.queryUserByMobile(mobile);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
