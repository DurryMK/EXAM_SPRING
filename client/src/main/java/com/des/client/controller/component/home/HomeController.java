package com.des.client.controller.component.home;

import com.des.client.consts.Tag;
import com.des.client.consts.Res;
import com.des.client.controller.system.AbstractController;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.service.system.CommonService;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/home")
public class HomeController extends AbstractController {

    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping("/initPage")
    public Map initPage(HttpServletRequest request, Emap em) {
        User user = null;
        try {
            user = (User)request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            if (null == user) {
                return em.fail("未登录");
            }
            //获取最新的用户信息
            user = commonService.getUserInfoWithHandle(user);
            if(user == null){
                return em.fail("用户信息错误");
            }
            return em.successJ(user);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常");
        }
    }

    @RequestMapping("/personal/initPage")
    public Map personal(HttpServletRequest request, Emap em) {
        try {
            User user = (User)request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            if (null == user) {
                return em.fail("未登录");
            }
            //获取最新的用户信息
            user = commonService.getUserInfoWithHandle(user);
            if(user == null){
                return em.fail("未找到用户");
            }
            return em.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常");
        }
    }
}
