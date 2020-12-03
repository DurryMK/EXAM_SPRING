package com.des.client.serviceImpl.system;

import com.des.client.conf.ResConst;
import com.des.client.entity.system.User;
import com.des.client.mapper.Login.UserMapper;
import com.des.client.utils.commonUtils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 快速登录
     */
    public void quickLogin(String mobile, String vcode, Map map, HttpServletRequest request) {
        if (!redisUtil.hasKey(ResConst.VERIFYTOKEN + mobile)) {//1.验证码是否过期
            map.put(ResConst.RESTOKEN, ResConst.FAIL);
            map.put(ResConst.RESINFO, "验证码已失效，请重新获取");
        } else {
            //获取缓存的验证码
            String orgVocde = redisUtil.get(ResConst.VERIFYTOKEN + mobile);
            if (!orgVocde.equals(vcode)) {//2.验证码是否正确
                map.put(ResConst.RESTOKEN, ResConst.FAIL);
                map.put(ResConst.RESINFO, "验证码错误");
            } else {
                //获取用户信息
                User user = null;
                try {
                    user = userMapper.queryUserByMobile(mobile);
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put(ResConst.RESTOKEN, ResConst.FAIL);
                    map.put(ResConst.RESINFO, "登录失败，系统异常");
                }
                if (user == null) {//3.用户是否存在
                    map.put(ResConst.RESTOKEN, ResConst.FAIL);
                    map.put(ResConst.RESINFO, "用户不存在");
                } else {
                    //登录成功之后删除验证码
                    redisUtil.delete(ResConst.VERIFYTOKEN + mobile);
                    //缓存用户登录记录
                    request.getSession().setAttribute(ResConst.USERLOGINTOKEN, user);
                    //登录成功
                    map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
                    map.put(ResConst.RESINFO, "登录成功");
                }
            }
        }
    }

    /**
     * 自动登录
     */
    public Map autoLogin(String mobile, Map map, HttpServletRequest request) {
        //获取用户信息
        User user = userMapper.queryUserByMobile(mobile);
        //获取缓存的验证码
        if (user == null) {
            map.put(ResConst.RESTOKEN, ResConst.FAIL);
            map.put(ResConst.RESINFO, "用户信息验证失败！请重新登录");
            return map;
        }
        //登录成功之后删除验证码
        redisUtil.delete(ResConst.VERIFYTOKEN + mobile);
        //缓存用户登录记录
        request.getSession().setAttribute(ResConst.USERLOGINTOKEN, mobile);
        //返回用户信息
        map.put(ResConst.RESINFO, userInfoConvers(user));
        map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
        return map;
    }

    /**
     * 处理用户信息
     */
    private Map userInfoConvers(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", user.getUsername());
        map.put("mobile", user.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        map.put("qq", user.getQq());
        map.put("email", user.getEmail());
        return map;
    }
}
