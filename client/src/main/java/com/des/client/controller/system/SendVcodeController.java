package com.des.client.controller.system;

import com.des.client.consts.Res;
import com.des.client.consts.Tag;
import com.des.client.entity.system.Emap;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.serviceImpl.system.LoginServiceImpl;
import com.des.client.utils.SMSUtils.SMSUtils;
import com.des.client.utils.commonUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller()
@RequestMapping("/svode")
public class SendVcodeController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommonServiceImpl commonService;
    @Autowired
    private LoginServiceImpl loginService;

    private final int VCODE_TIME = 3000; //验证码过期时间 单位（分钟）

    @RequestMapping("/sendLoginVcode")
    public @ResponseBody
    Map sendSMS(@RequestParam String mobile, Emap em, HttpServletRequest request) {
        try {
            //获取发送的密钥
            Map keyMap = commonService.getALiyunKey();
            String code = SMSUtils.SendVerifyCode(mobile, keyMap);
            System.out.println(mobile + "发送的验证码：" + code);
            if (code == null) {
                //发送失败
                return em.fail();
            } else {
                //验证码存入缓存
                redisUtil.set(Tag.VERIFY_TOKEN + mobile, code);
                //设置过期时间 5分钟
                redisUtil.expire(Tag.VERIFY_TOKEN + mobile, VCODE_TIME, TimeUnit.SECONDS);
                //发送成功
                return em.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail();
        }
    }
}
