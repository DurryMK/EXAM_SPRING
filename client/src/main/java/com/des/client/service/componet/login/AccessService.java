package com.des.client.service.componet.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/16 7:36
 */
@Service
public interface AccessService{
    /**
     *快速登录
     * */
    void quickLogin(String mobile, String vcode, Map map);

    /**
     *自动登录
     * */
    Map autoLogin(String token, Map map);
}
