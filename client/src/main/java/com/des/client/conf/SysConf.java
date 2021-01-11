package com.des.client.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/11 14:43
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class SysConf {
    @Value("${custom.env}")
    private String env;

    @RequestMapping("/getEnv")
    public String get() {
        return env;
    }
}
