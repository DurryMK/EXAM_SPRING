package com.des.client.serviceImpl.system;

import com.des.client.conf.ResConst;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.utils.commonUtils.AesCodeUtil;
import com.des.client.utils.commonUtils.RedisUtil;
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
    @Autowired
    private AesCodeUtil aesCodeUtil;

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
                    System.out.println(request.getSession().getAttribute(ResConst.USERLOGINTOKEN));
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
    public Map autoLogin(String token, Map map, HttpServletRequest request) {
        try {
            //1.解析token
            token = aesCodeUtil.AESDncode(token);
            //2.分解token信息  用户名；密码；有效时间；登录方式
            String[] infos = token.split(";");
            String username = infos[0];
            String pwd = infos[1];
            String time = infos[2];
            String type = infos[3];
            long now = System.currentTimeMillis();//获取当前时间
            User user = null;
            if (now < Long.parseLong(time)) {//3.校验有效时间是否过期
                //4.判断上次登录的方式
                if ("Q".equals(type)) {
                    user = userMapper.queryUserByMobile(username);
                } else if ("L".equals(type)) {
                    user = userMapper.queryUserByPwd(username, pwd);
                } else {
                    map.put(ResConst.RESTOKEN, ResConst.FAIL);
                }
            } else {
                //已过期
                map.put(ResConst.RESTOKEN, ResConst.FAIL);
            }
            if (user != null) {
                request.getSession().setAttribute(ResConst.USERLOGINTOKEN, user);
                map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
            } else {
                map.put(ResConst.RESTOKEN, ResConst.FAIL);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            map.put(ResConst.RESTOKEN, ResConst.FAIL);
        }
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
