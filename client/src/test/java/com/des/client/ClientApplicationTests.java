package com.des.client;

import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.utils.commonUtils.GenID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {
@Autowired
private UserMapper um;
    @Test
    void contextLoads() {
        System.out.println(um.queryUserByMobile("15974076596"));
        User user = new User();
        user.setId(GenID.AID());
        user.setUsername("NZ");
        user.setPwd("123");
        user.setMobile("1112233241124");
        System.out.println(user);
        um.addUser(user);
        System.out.println(um.queryAllUser());
    }

}
