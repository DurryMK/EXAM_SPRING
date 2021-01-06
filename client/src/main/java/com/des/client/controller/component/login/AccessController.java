package com.des.client.controller.component.login;

import com.ctc.wstx.util.StringUtil;
import com.des.client.consts.Res;
import com.des.client.consts.Tag;
import com.des.client.controller.system.AbstractController;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import com.des.client.utils.commonUtils.AesCodeUtil;
import com.des.client.utils.commonUtils.GenCookieToken;
import com.des.client.utils.commonUtils.RSAUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller()
@RequestMapping("/access")
public class AccessController extends AbstractController {
    @Autowired
    AesCodeUtil aesCodeUtil;
    @Autowired
    private LoginServiceImpl loginService;
    @Resource
    private UserMapper um;

    private final int expireTime = 3 * 24 * 60 * 60;

    @RequestMapping("/quickLogin")
    @ResponseBody
    public Map quickLogin(HttpServletRequest request, Emap em, HttpServletResponse response) {
        String mobile = request.getParameter("mobile");
        String vcode = request.getParameter("vcode");
        try {
            //快速登录
            loginService.quickLogin(mobile, vcode, em);
            if (Res.SUCCESS.equals(em.token())) {
                //登录成功 保存登录信息
                request.getSession().setAttribute(Tag.USER_LOGIN_TOKEN, em.info());
                // 生成登录记录 保存到Redis 缓存3天
                String token = UUID.randomUUID().toString();
                redisUtil.set(token, mobile, 3, TimeUnit.DAYS);
                //保存到cookie 3天 实现3天内同一客户端可自动登录
                Cookie cookie = new Cookie(Tag.COOKIE_KEY, token);
                cookie.setMaxAge(expireTime);
                cookie.setPath("/exam_du");
                response.addCookie(cookie);
                //生成密钥对
                Map map = RSAUtil.genKeyPair();
                //保存私钥到session
                request.getSession().setAttribute(Tag.PRIVATEKEY,map.get(RSAUtil.PRIVATE));
                //保存公钥到session
                request.getSession().setAttribute(Tag.PUBLICKEY,map.get(RSAUtil.PUBLIC));
                //公钥返回到客户端
                return em.success(map.get(RSAUtil.PUBLIC));
            }else{
                return em.back();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常");
        }
    }

    /**
     * 根据Cookie的登录记录进行自动登录
     */
    @RequestMapping("/autoLogin")
    public @ResponseBody
    Map autoLogin(HttpServletRequest request, Emap em, User user) {
        String token = null;
        try {
            //取出Cookie中的登录token
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (Tag.COOKIE_KEY.equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            } else {
                return em.fail("无登录记录");
            }
            if (StringUtils.isEmpty(token)) {
                return em.fail("登录凭证为空");
            }
            //自动登录
            loginService.autoLogin(token, em);
            if (!Res.SUCCESS.equals(em.token())) {
                return em.back();
            }
            //登录成功
            String mobile = (String) em.info();
            //获取用户信息 保存到Session
            user.setMobile(mobile);
            user = commonService.getUserInfo(user);
            request.getSession().setAttribute(Tag.USER_LOGIN_TOKEN, user);
            //生成密钥对
            Map map = RSAUtil.genKeyPair();
            //保存私钥到session
            request.getSession().setAttribute(Tag.PRIVATEKEY,map.get(RSAUtil.PRIVATE));
            //保存公钥到session
            request.getSession().setAttribute(Tag.PUBLICKEY,map.get(RSAUtil.PUBLIC));
            return em.success(map.get(RSAUtil.PUBLIC));
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常");
        }
    }

    @RequestMapping("/exitLogin")
    public @ResponseBody
    void exitLogin(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute(Tag.USER_LOGIN_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
