package com.des.client.controller.system;

import com.des.client.consts.Tag;
import com.des.client.entity.system.Emap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 主控制类
 * 用于初始化主页面
 */
@RestController()
@RequestMapping("/app")
public class AppController extends AbstractController {

    @RequestMapping("/initPage")
    public Map initPage(HttpServletRequest request, Emap em) {
        try {
            Object user = request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            if (user == null) {
                return em.fail("未登录");
            }
            Object publicKey = request.getSession().getAttribute(Tag.PUBLICKEY);
            return em.success(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
            return em.success("系统异常");
        }
    }

    @RequestMapping("/test")
    public Map test(HttpServletRequest request, Emap em) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for (MultipartFile mf : files){
        }
        return em.success("系统异常");
    }
}
