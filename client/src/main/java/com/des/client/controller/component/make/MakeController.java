package com.des.client.controller.component.make;

import com.des.client.consts.Res;
import com.des.client.consts.Tag;
import com.des.client.controller.system.AbstractController;
import com.des.client.entity.paper.Paper;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.User;
import com.des.client.mapper.system.UserMapper;
import com.des.client.service.componet.make.MakeService;
import com.des.client.serviceImpl.make.MakeServiceImpl;
import com.des.client.utils.commonUtils.GenID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("/make")
public class MakeController extends AbstractController {

    @Autowired
    private MakeService makeService;

    @RequestMapping("/makeFirstStep")
    public Map makeFirstStep(HttpServletRequest request, Emap em) {
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String remark = request.getParameter("remark");
        String level = request.getParameter("level");
        String code = request.getParameter("code");
        try {
            //获取创建者的信息
            User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            //创建者ID
            String owner = user.getId();
            //组装试卷的基本信息
            Paper paper = new Paper();
            paper.setTitle(title);
            paper.setOwner(owner);
            paper.setLevel(level);
            paper.setRemark(remark);
            paper.setType(type);
            paper.setCode(code);

            if(StringUtils.isEmpty(title)||StringUtils.isEmpty(code)){
                return em.fail("新建试卷失败，请重试");
            }
            //获取正在创建的试卷的code 只要code相同则判断为同一次操作
            String preCode = request.getSession().getAttribute(Tag.PAPER_CODE + user.getMobile())+"";
            //preCode与code不相等 表示正在新建试卷
            if(!StringUtils.equals(code,preCode)){
                paper = makeService.saveMakeFirst(paper);
                request.getSession().setAttribute(Tag.PAPER_CODE + user.getMobile(),code);
            }else{
                //preCode与code相等 表示该操作为更新试卷
                paper = makeService.updateMakeFirst(paper);
            }
            if(paper == null){
                return em.fail("试卷保存失败，请稍后再试");
            }
            return em.success("试卷已保存");
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("试卷保存失败，请稍后再试");
        }
    }
}
