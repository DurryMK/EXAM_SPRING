package com.des.client.serviceImpl.common;

import com.des.client.consts.Res;
import com.des.client.consts.Tag;
import com.des.client.entity.system.User;
import com.des.client.interfaceUtils.iBaidu.GenBaiduToken;
import com.des.client.mapper.system.TokenMapper;
import com.des.client.mapper.system.UserMapper;
import com.des.client.service.system.CommonService;
import com.des.client.utils.commonUtils.AgeUtil;
import com.des.client.utils.commonUtils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 用于公共服务
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private TokenMapper tokenMapper;
    @Resource
    private UserMapper userMapper;

    private Random random = new Random();

    @Override
    public User getUserInfo(User user) {
        return userMapper.queryUser(user);
    }

    @Override
    public User getUserInfoWithHandle(User user) {
        user = userMapper.queryUser(user);
        if (user == null) {
            return null;
        }
        //隐藏ID
        user.setId(random.nextInt(10000) + "");
        //隐藏手机号中间4位
        user.setMobile(user.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        //计算当前年龄
        user.setBirthday(AgeUtil.get(user.getBirthday()) + "");
        return user;
    }

    /**
     * 读取aliyun密钥
     */
    public Map getALiyunKey() {
        Map<String, String> resultMap = new HashMap<>();
        String key = redisUtil.get(Tag.ALIYUN_ACCESSKEY_TOKEN);
        String secret = redisUtil.get(Tag.ALIYUN_SECRET_TOKEN);
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(secret)) {
            //读取数据库存入缓存 缓存30天
            key = tokenMapper.queryToken(Tag.ALIYUN_ACCESSKEY_TOKEN).getToken();
            secret = tokenMapper.queryToken(Tag.ALIYUN_SECRET_TOKEN).getToken();
            redisUtil.set(Tag.ALIYUN_ACCESSKEY_TOKEN, key, 30, TimeUnit.DAYS);
            redisUtil.set(Tag.ALIYUN_SECRET_TOKEN, secret, 30, TimeUnit.DAYS);
        }
        resultMap.put(Tag.ALIYUN_ACCESSKEY_TOKEN, key);
        resultMap.put(Tag.ALIYUN_SECRET_TOKEN, secret);
        return resultMap;
    }

    /**
     * 读取baidu Api Token
     */
    public Map genBaiduToken() {
        Map<String, String> resultMap = new HashMap<>();
        String token = "";
        try {
            if (redisUtil.hasKey(Tag.BAIDU_TOKEN)) {
                //从redis读取百度token
                token = redisUtil.get(Tag.BAIDU_TOKEN);
            } else {
                //如果没有或者已经过期  则重新拉取token
                token = GenBaiduToken.getAuth();
                //存入redis 缓存25天
                redisUtil.set(Tag.BAIDU_TOKEN, token);
                redisUtil.expire(Tag.BAIDU_TOKEN, 25, TimeUnit.DAYS);
            }
            resultMap.put(Res.RESTOKEN, Res.SUCCESS);
        } catch (Exception e) {
            resultMap.put(Res.RESTOKEN, Res.FAIL);
            e.printStackTrace();
        }
        resultMap.put(Tag.BAIDU_TOKEN, token);
        return resultMap;
    }

    /**
     * 读取aes Token
     */
    public String genAesToken() {
        String token = "";
        try {
            if (redisUtil.hasKey(Tag.AES_KEY)) {
                //从redis读取token
                token = redisUtil.get(Tag.AES_KEY);
            } else {
                token = tokenMapper.queryToken("aes").getToken();
                //存入redis
                redisUtil.set(Tag.AES_KEY, token);
                redisUtil.expire(Tag.AES_KEY, 3, TimeUnit.DAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
