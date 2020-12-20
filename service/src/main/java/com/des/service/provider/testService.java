package com.des.service.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/15 16:40
 */
@FeignClient(value = "client",path = "/exam_du")
public interface testService {
    @RequestMapping(value = "/app/test", method = RequestMethod.GET)
    String getHost(String name);
}
