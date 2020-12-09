package com.des.client.controller.component.home;

import com.des.client.conf.ResConst;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import com.des.client.utils.commonUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommonServiceImpl commonService;
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private UserMapper um;

    @RequestMapping("/initPage")
    public Map initPage(HttpServletRequest request, Map map) {
        map = new HashMap<>();
        try {
            User user = (User)request.getSession().getAttribute(ResConst.USERLOGINTOKEN);
            if (null == user) {
                map.put(ResConst.RESTOKEN, ResConst.FAIL);
                map.put(ResConst.RESINFO, "未登录");
                return map;
            }
            //获取最新的用户信息
            user = um.queryUserByMobile(user.getMobile());
            if(user == null){
                map.put(ResConst.RESTOKEN, ResConst.FAIL);
                map.put(ResConst.RESINFO, "未登录");
                return map;
            }
            user.setMobile(user.getMobile().replace("(\\\\d{3})\\\\d{4}(\\\\d{4})", "$1****$2"));
            map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
            map.put(ResConst.RESINFO, "已登录");
            map.put("user",user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(ResConst.RESTOKEN, ResConst.FAIL);
            map.put(ResConst.RESINFO, "系统异常");
        }
        return map;
    }

    @RequestMapping("/personal/initPage")
    public Map personal(HttpServletRequest request, Map map) {
        map = new HashMap<>();
        try {
            User user = (User)request.getSession().getAttribute(ResConst.USERLOGINTOKEN);
            if (null == user) {
                map.put(ResConst.RESTOKEN, ResConst.FAIL);
                map.put(ResConst.RESINFO, "未登录");
                return map;
            }
            //获取最新的用户信息
            user = um.queryUserByMobile(user.getMobile());
            if(user == null){
                map.put(ResConst.RESTOKEN, ResConst.FAIL);
                map.put(ResConst.RESINFO, "未登录");
                return map;
            }
            user.setMobile(user.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
            map.put(ResConst.RESINFO,user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(ResConst.RESTOKEN, ResConst.FAIL);
            map.put(ResConst.RESINFO, "系统异常");
        }
        return map;
    }
}
