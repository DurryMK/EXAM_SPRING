package com.des.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/15 16:42
 */
@RestController
public class testController {
    @Autowired
    private testService test;

    @RequestMapping(value = "/client/postPerson", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String postPerson(String name) {
        return "";
    }
}
