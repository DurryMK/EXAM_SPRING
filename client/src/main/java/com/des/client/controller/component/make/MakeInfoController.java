package com.des.client.controller.component.make;

import com.des.client.consts.Tag;
import com.des.client.controller.system.AbstractController;
import com.des.client.entity.paper.PaperType;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.service.componet.make.MakeInfoService;
import com.des.client.service.componet.make.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/18 21:48
 */
@RestController()
@RequestMapping("/make/info")
@RefreshScope
public class MakeInfoController extends AbstractController {
    @Autowired
    private MakeService makeService;

    @Autowired
    private MakeInfoService makeInfoService;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/getUserTypeList")
    public Map makeFirstStep(HttpServletRequest request, Emap em) {
        try{
            User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            if(user == null){
                return em.fail("未登录");
            }
            List<PaperType> typeList = makeInfoService.getTypeList(user.getId());
            return em.success(typeList);
        }catch (Exception e){
            e.printStackTrace();
            return em.fail();
        }
    }
}
