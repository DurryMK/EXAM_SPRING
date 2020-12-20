package com.des.client.controller.system;

import com.des.client.consts.Res;
import com.des.client.consts.Tag;
import com.des.client.entity.system.Emap;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import com.des.client.utils.commonUtils.RedisUtil;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 主控制类
 * 用于初始化主页面
 */
@RestController()
@RequestMapping("/app")
public class AppController extends AbstractController {

    @RequestMapping("/initPage")
    public Map initPage(HttpServletRequest request, Emap em) {
        try {
            Object user = request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            if (user == null) {
                return em.fail("未登录");
            }
            return em.success("已登录");
        } catch (Exception e) {
            e.printStackTrace();
            return em.success("系统异常");
        }
    }
}
