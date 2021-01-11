package com.des.client.serviceImpl.login;

import com.des.client.consts.Tag;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.utils.commonUtils.AesCodeUtil;
import com.des.client.utils.commonUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public void quickLogin(String mobile, String vcode, Emap em) {
        //1.验证码是否过期
        if (!redisUtil.hasKey(Tag.VERIFY_TOKEN + mobile)) {
            em.fail("验证码已失效，请重新获取");
        } else {
            //2.获取缓存的验证码
            String orgVocde = redisUtil.get(Tag.VERIFY_TOKEN + mobile);
            //3.验证码是否正确
            if (!orgVocde.equals(vcode)) {
                em.fail("验证码错误");
            } else {
                //获取用户信息
                User user = new User() ;
                try {
                    user.setMobile(mobile);
                    user = userMapper.queryUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    em.fail("登录失败，系统异常");
                }
                if (user == null) {//3.用户是否存在
                    em.fail("用户不存在");
                } else {
                    //登录成功
                    em.success(user);
                }
            }
        }
    }

    /**
     * 自动登录
     */
    public Map autoLogin(String token, Emap em) {
        try {
            //1.从redis读取登录token
            if (!redisUtil.hasKey(token)) {
                return em.fail("登录已过期");
            }
            //2.读取登录信息
            String mobile = redisUtil.get(token);
            //3.登录成功
            return em.success(mobile);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常");
        }
    }
}
