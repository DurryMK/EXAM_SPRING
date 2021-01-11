package com.des.client.core.aspect;

import com.des.client.consts.Tag;
import com.des.client.entity.system.Emap;
import com.des.client.utils.commonUtils.RSAUtil;
import com.des.client.utils.commonUtils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/26 11:36
 */
@Aspect
@Component
public class PermissionAccess {

    @Autowired
    private RedisUtil redisUtil;

    private final String AUTHOR = "authorization";

    private final String AUTHOR2USER = "AUTHOR2USER_";

    private final long OVERTIME = 3 * 60 * 1000;//请求失效时间

    private final String SPLIT = ";";//签名分隔符

    private Logger logger = LoggerFactory.getLogger(PermissionAccess.class);

    @Pointcut("execution(* com.des.client.controller.component..*.*(..))")
    private void PermissionAccess() {
    }

    @Around("PermissionAccess()")
    public Object process(ProceedingJoinPoint joinPoint) {
        //响应结果
        Emap em = new Emap();
        try {
            //获取方法参数
            Object[] args = joinPoint.getArgs();//
            HttpServletRequest request = null;
            for (Object o : args) {
                if (o instanceof HttpServletRequest) {
                    //获取请求内容
                    request = (HttpServletRequest) o;
                    break;
                }
            }
            if (request != null) {
                //获取请求的URL
                String URL = request.getRequestURL().toString();
                String Origin = request.getHeader("Origin");
                logger.debug("Request From [ "+Origin+" ]，Target : "+URL);
                //获取用户信息
                Object userInfo = request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
                //对除登录请求以外进行登录验证
                if (URL.contains("/access")) {
                    if (!URL.contains("/exitLogin")) {
                        if (userInfo != null) {
                            return em.fail("已经登录");
                        }
                    }
                } else {
                    //非登录请求验证是否已经登录
                    if (userInfo != null) {
                        //已登录的请求 进行签名验证
                        if (!authorVerify(request))
                            return em.fail("无效的请求");
                    } else {
                        return em.fail("未登录");
                    }
                }
            } else {
                return em.fail("请求异常");
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return em.fail("系统异常");
        }
    }

    /**
     * 用户每次登录时，生成密钥对传递到前端页面保存，之后的请求使用公钥加密签名之后添加到请求头，后端使用私钥解密。
     * <p>
     * 签名校验
     */
    private boolean authorVerify(HttpServletRequest request) {
        try {
            //请求头中必须含有Authorization
            if (request.getHeader(AUTHOR) == null || "".equals(request.getHeader(AUTHOR)))
                return false;
            //获取签名
            String author = request.getHeader(AUTHOR);
            //获取session中的私钥
            String privateKey = (String) request.getSession().getAttribute(Tag.PRIVATEKEY);
            //对签名解密
            author = RSAUtil.decrypt(author, privateKey).replaceAll("\"", "");
            //签名不为空且必须含有分隔符
            if (StringUtils.isNotEmpty(author) && author.contains(SPLIT)) {
                //签名结构 请求发出时间;唯一标识符
                String[] split = author.split(SPLIT);
                String startTime = split[0];
                String auId = split[1];
                //当前时间与请求时间的时间差
                long l = System.currentTimeMillis() - Long.parseLong(startTime);
                if (l > OVERTIME) {
                    //超时
                    return false;
                }
                //获取用户与签名集合的对应标识符
                String author2User = request.getSession().getAttribute(AUTHOR2USER) + "";
                if (redisUtil.hasKey(Tag.AUTHOR_TOKEN + author2User)) {
                    //获取redis保存的签名标识符集合 -1代表返回所有
                    List<String> auList = redisUtil.lRange(Tag.AUTHOR_TOKEN + author2User, 0, -1);
                    //遍历比较 ， 如果有相同的则代表是旧的请求
                    for (String au : auList) {
                        if (auId.equals(au)) {
                            return false;
                        }
                    }
                    //请求通过 auId添加到已失效列表
                    redisUtil.lRightPush(Tag.AUTHOR_TOKEN + author2User, auId);
                } else {
                    //如果没有对应的key ， 则新建一个标识符列表 ， 并将本次请求的唯一标识符添加到列表
                    List<String> auList = new ArrayList<>();
                    auList.add(auId);
                    //在Session中维护一个与用户唯一对应的UUID  作为用户请求API的凭证
                    author2User = UUID.randomUUID().toString();
                    request.getSession().setAttribute(AUTHOR2USER, author2User);
                    redisUtil.lRightPushAll(Tag.AUTHOR_TOKEN + author2User, auList);
                    //设置列表的过期时间
                    redisUtil.expire(Tag.AUTHOR_TOKEN + author2User, OVERTIME, TimeUnit.MILLISECONDS);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
