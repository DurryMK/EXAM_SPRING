package com.des.client.controller.system;

import com.des.client.consts.Tag;
import com.des.client.entity.system.Emap;
import com.des.client.utils.SMSUtils.SMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller()
@RequestMapping("/svode")
@RefreshScope
public class SendVcodeController extends AbstractController {

    private final int VCODE_TIME = 3000; //验证码过期时间 单位（分钟）

    private final String LOCK = "VCODELOCK_";

    private final int LOCK_TIME = 60;

    private Logger logger = LoggerFactory.getLogger(SendVcodeController.class);

    @RequestMapping("/sendLoginVcode")
    public @ResponseBody
    Map sendSMS(@RequestParam String mobile, Emap em, HttpServletRequest request) {
        if (redisUtil.hasKey(LOCK + mobile)) {
            //发送失败
            return em.fail("请勿频繁操作，1分钟后可再次发送");
        }
        //获取发送的密钥
        Map keyMap = commonService.getALiyunKey();
        String code = SMSUtils.SendVerifyCode(mobile, keyMap);
        if (code == null) {
            //发送失败
            return em.fail("请稍后再试");
        } else {
            //验证码存入缓存 设置过期时间 5分钟
            redisUtil.set(Tag.VERIFY_TOKEN + mobile, code, VCODE_TIME, TimeUnit.SECONDS);
            //加锁 1分钟内不能重复发送
            redisUtil.set(LOCK + mobile, code, LOCK_TIME, TimeUnit.SECONDS);
            //发送成功
            return em.success(code);
        }
    }
}
