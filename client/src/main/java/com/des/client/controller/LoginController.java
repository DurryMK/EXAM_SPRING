package com.des.client.controller;

import com.des.client.conf.ResConst;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import com.des.client.utils.commonUtils.AesCodeUtil;
import com.des.client.utils.commonUtils.GenCookieToken;
import com.des.client.utils.commonUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller()
@RequestMapping("/access")
public class LoginController {
    @Autowired
    AesCodeUtil aesCodeUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommonServiceImpl commonService;
    @Autowired
    private LoginServiceImpl loginService;
    @Resource
    private UserMapper um;

    @RequestMapping("/quickLogin")
    @ResponseBody
    public Map quickLogin(HttpServletRequest request, Map map, HttpServletResponse response) {
        map = new HashMap<String,Object>();
        String mobile = request.getParameter("mobile");
        String vcode = request.getParameter("vcode");
        try {
            //快速登录
            loginService.quickLogin(mobile, vcode, map, request);
            if(!map.get(ResConst.RESTOKEN).equals("-1")){
                //生成登录cookie
                String token = aesCodeUtil.AESEncode(GenCookieToken.genToken(mobile, "", "Q"));
                Cookie cookie = new Cookie(ResConst.cookieKey, token);
                cookie.setMaxAge(3 * 24 * 60 * 60);
                response.addCookie(cookie);
                map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
                map.put(ResConst.RESINFO, "登录成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(ResConst.RESTOKEN, ResConst.FAIL);
        }
        return map;
    }

    /**
     * 根据Cookie的登录记录进行自动登录
     */
    @RequestMapping("/autoLogin")
    public @ResponseBody
    Map autoLogin(HttpServletRequest request, Map map) {
        map = new HashMap<String,Object>();
        String token = request.getParameter("token");
        try {
            //自动登录
            loginService.autoLogin(token, map, request);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(ResConst.RESTOKEN, ResConst.FAIL);
        }
        return map;
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
