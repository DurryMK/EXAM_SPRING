package com.des.client.utils.commonUtils;

public class GenCookieToken {
    public static final long expireTime = 3 * 24 * 60 * 60 * 1000;//过期时间 3 天 （毫秒）

    public static String genToken(String username, String pwd, String type) {
        String endTime = (System.currentTimeMillis() + expireTime) + "";
        System.out.println("结束时间：" + endTime);
        String token = username + ";" + pwd + ";" + endTime + ";" + type;
        return token;
    }
}
