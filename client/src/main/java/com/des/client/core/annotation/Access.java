package com.des.client.core.annotation;

import java.lang.annotation.*;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/26 11:28
 */
@Target({ElementType.METHOD,ElementType.TYPE})//可用于方法和类
@Retention(RetentionPolicy.RUNTIME)//运行时使用
@Documented
public @interface Access {
    /**
     * 访问权限控制
     * */
}
