package com.des.client.service.system;

import com.des.client.entity.system.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/16 7:36
 * 用于公有服务
 */
@Service
public interface CommonService {
    /**
     * 获取用户信息
     */
    User getUserInfo(User user);
    /**
     * 获取用户信息 并对用户信息进行处理
     */
    User getUserInfoWithHandle(User user);

    /**
     * 获取阿里云密钥
     */
    Map getALiyunKey();

    /**
     * 获取百度token
     */
    Map genBaiduToken();

    /**
     *获取AES解密
     * */
    Map genAesToken();
}
