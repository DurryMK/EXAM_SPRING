package com.des.client.controller.component.make;

import com.des.client.consts.Tag;
import com.des.client.controller.system.AbstractController;
import com.des.client.entity.paper.Invigilate;
import com.des.client.entity.paper.Paper;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.User;
import com.des.client.service.componet.make.MakeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController()
@RequestMapping("/make")
@RefreshScope
public class MakeController extends AbstractController {

    @Autowired
    private MakeService makeService;

    @RequestMapping("/makeFirstStep")
    public Map makeFirstStep(HttpServletRequest request, Emap em, Paper paper) {
        //获取创建者的信息
        User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
        //创建者ID
        String owner = user.getId();
        paper.setOwner(owner);
        if (StringUtils.isEmpty(paper.getTitle()) || StringUtils.isEmpty(paper.getCode())) {
            return em.fail("试卷保存失败，请重试");
        }
        //获取正在创建的试卷的code 只要code相同则判断为同一次操作
        String preCode = request.getSession().getAttribute(Tag.PAPER_CODE) + "";
        //preCode与code不相等 表示正在新建试卷
        if (!StringUtils.equals(paper.getCode(), preCode)) {
            request.getSession().setAttribute(Tag.PAPER_CODE, paper.getCode());
            return makeService.savePaper(paper, em);
        } else {
            //preCode与code相等 表示该操作为更新试卷
            return makeService.updatePaper(paper, em);
        }
    }

    @RequestMapping("/makeSecondStep")
    public Map makeSecondStep(HttpServletRequest request, Emap em, Invigilate invigilates) {
        //获取创建者的信息
        User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
        if (user == null)
            return em.fail("操作失败，请先登录");
        //当前正在创建的试卷code
        String paperCode = request.getSession().getAttribute(Tag.PAPER_CODE) + "";
        if ("".equals(paperCode))
            return em.fail("操作失败，试卷不存在");
        //创建者ID
        String owner = user.getId();
        makeService.updateMakSecond(invigilates, paperCode, owner, em);
        if (invigilates == null) {
            return em.fail("保存设置失败，请稍后再试");
        }
        return em.success("保存设置成功");
    }
}
