package com.des.client.controller.component.home;

import com.des.client.consts.Tag;
import com.des.client.controller.system.AbstractController;
import com.des.client.core.exception.RunningException;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.User;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController()
@RequestMapping("/home")
@RefreshScope
public class HomeController extends AbstractController {

    @RequestMapping("/initPage")
    public Map initPage(HttpServletRequest request, Emap em) throws RunningException {
        User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
        //获取最新的用户信息
        user = commonService.getUserInfoWithHandle(user);
        if (user == null) {
            throw new RunningException("用户信息错误");
        }
        return em.successJ(user);
    }

    @RequestMapping("/personal/personalInfo")
    public Map personal(HttpServletRequest request, Emap em) throws RunningException {
        User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
        //获取最新的用户信息
        user = commonService.getUserInfoWithHandle(user);
        if (user == null) {
            throw new RunningException("用户信息错误");
        }
        return em.success(user);
    }
}
