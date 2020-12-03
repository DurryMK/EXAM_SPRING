package com.des.client.utils.SMSUtils;

import java.util.Map;

public class SMSUtils {

    /**
     * 发送验证码的模板
     */
    private static final String VERIFYCODEMODEL = "SMS_205408879";

    /**
     * 发送短信的工具类
     *
     * @return
     * @Param mobile 手机号
     * @Param type 发送的短信类型
     */
    public static String SendVerifyCode(String mobile, Map keyMap) {
        //发送验证码
        String code = genVcode();
        String result = PhoneCode.sendSMS(mobile, VERIFYCODEMODEL, code, keyMap);
        if (result.equals(StaticPeram.SENDFAIL)) {
            return null;
        }
        return code;
    }

    /**
     * 生成验证码
     *
     * @return
     */
    private static String genVcode() {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }
}
