package com.des.client.controller.component.paper;

import com.des.client.consts.Tag;
import com.des.client.controller.system.AbstractController;
import com.des.client.entity.paper.Paper;
import com.des.client.entity.paper.condition.PaperCondition;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.PageModel;
import com.des.client.entity.system.User;
import com.des.client.service.componet.paper.PaperService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/29 18:58
 */
@RestController()
@RequestMapping("/paper")
@RefreshScope
public class PaperController extends AbstractController {
    @Resource
    private PaperService paperService;

    @RequestMapping("/getPaperList")
    public Map makeFirstStep(HttpServletRequest request, Emap em, PaperCondition paperCondition) {
        try {
            //获取创建者的信息
            User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            //创建者ID
            paperCondition.setOwner(user.getId());
            //获取试卷列表
            List<Paper> paperList = paperService.getPaperList(paperCondition);
            //从试卷列表中获取试卷类型集合
            Set typeSet = new HashSet<String>();
            for (Paper paper : paperList) {
                typeSet.add(paper.getType());
            }
            int total = paperService.getTotalWithCondition(paperCondition);
            paperCondition.setTotal(total);
            //页面数据模型
            PageModel model = new PageModel();
            Map data = new HashMap<String, Object>();
            data.put("list",paperList);
            data.put("types",typeSet);
            model.setData(data);
            model.setCondition(paperCondition);
            return em.success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常，请稍后再试");
        }
    }
}
