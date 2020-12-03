package com.des.client.serviceImpl.common;

import com.des.client.conf.ResConst;
import com.des.client.interfaceUtils.iBaidu.GenBaiduToken;
import com.des.client.utils.commonUtils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用于公共服务
 */
@Service
public class CommonServiceImpl {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 读取aliyun密钥
     */
    public Map getALiyunKey() {
        Map<String, String> resultMap = new HashMap<>();
        String key = redisUtil.get(ResConst.ALIYUNACKEYTOKEN);
        String secret = redisUtil.get(ResConst.ALIYUNACKEYSECRTTOKEN);
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(secret)) {
            //重新读取数据库存入缓存

        }
        resultMap.put(ResConst.ALIYUNACKEYTOKEN, key);
        resultMap.put(ResConst.ALIYUNACKEYSECRTTOKEN, secret);
        return resultMap;
    }

    /**
     * 读取baidu Api Token
     */
    public Map genBaiduToken() {
        Map<String, String> resultMap = new HashMap<>();
        String token = "";
        try {
            if (redisUtil.hasKey(ResConst.BAIDUTOKEN)) {
                //从redis读取百度token
                token = redisUtil.get(ResConst.BAIDUTOKEN);
                System.out.println("读取百度token从redis");
            }else{
                System.out.println("读取百度token从百度api");
                //如果没有或者已经过期  则重新拉取token
                token = GenBaiduToken.getAuth();
                //存入redis 缓存25天
                redisUtil.set(ResConst.BAIDUTOKEN, token);
                redisUtil.expire(ResConst.BAIDUTOKEN,25, TimeUnit.DAYS);
            }
            resultMap.put(ResConst.RESTOKEN,ResConst.SUCCESS);
        } catch (Exception e) {
            resultMap.put(ResConst.RESTOKEN,ResConst.FAIL);
            e.printStackTrace();
        }
        resultMap.put(ResConst.BAIDUTOKEN, token);
        return resultMap;
    }

    /**
     * 读取aes Token
     */
    public String genAesToken() {
        String token = "";
        try {
            if (redisUtil.hasKey(ResConst.aesKey)) {
                //从redis读取token
                token = redisUtil.get(ResConst.aesKey);
                System.out.println("读取aes token从redis");
            }else{
                System.out.println("读取aes token从数据库");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
