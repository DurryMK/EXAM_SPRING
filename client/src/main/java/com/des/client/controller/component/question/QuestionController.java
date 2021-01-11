package com.des.client.controller.component.question;

import com.des.client.consts.Res;
import com.des.client.consts.Tag;
import com.des.client.controller.system.AbstractController;
import com.des.client.entity.PageContainer;
import com.des.client.entity.paper.Paper;
import com.des.client.entity.question.QuestionPre;
import com.des.client.entity.question.condition.QueCondition;
import com.des.client.entity.system.Emap;
import com.des.client.entity.system.PageModel;
import com.des.client.entity.system.User;
import com.des.client.service.question.QuestionInfoService;
import com.des.client.serviceImpl.question.QuestionInfoServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.*;
/**
 * @author durry
 * @version 1.0
 * @date 2021/01/06 17:39
 */
@RestController
@RequestMapping("/que")
@RefreshScope
public class QuestionController extends AbstractController {

    @Resource
    private QuestionInfoService questionService;
    /**
     * 初始化系统题目列表页面
     */
    @RequestMapping("/initPageSys")
    public Map initPageSys(HttpServletRequest request,Emap em, QueCondition condition) {
        try {
            //查询系统题库
            condition.setOwner(QueCondition.SYSTEM_OWNER);
            //获取题目列表
            List<QuestionPre> questionPreList = questionService.getQuestionList(condition);
            //从列表中获取类型集合
            Set typeSet = new HashSet<String>();
            for (QuestionPre pre : questionPreList) {
                typeSet.add(pre.getType());
            }

            //从列表中获取难度集合
            Set levelSet = new HashSet<String>();
            for (QuestionPre pre : questionPreList) {
                levelSet.add(pre.getLevel());
            }
            int total = questionService.getTotalWithCondition(condition);
            condition.setTotal(total);
            //页面数据模型
            PageModel model = new PageModel();
            Map data = new HashMap<String, Object>();
            data.put("list",questionPreList);
            data.put("types",typeSet);
            data.put("levels",levelSet);
            model.setData(data);
            model.setCondition(condition);
            return em.success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常，请稍后再试");
        }
    }

    /**
     * 初始化个人题目列表页面
     */
    @RequestMapping("/initPagePersonal")
    public Map initPagePersonal(HttpServletRequest request,Emap em, QueCondition condition) {
        try {
            User user = (User) request.getSession().getAttribute(Tag.USER_LOGIN_TOKEN);
            //查询个人题目列表
            condition.setOwner(user.getId());
            //获取该用户的题库总数
            int totalWithUser = questionService.getTotalWithUser(condition);
            //获取题目列表
            List<QuestionPre> questionPreList = questionService.getQuestionList(condition);
            //从列表中获取类型集合
            Set typeSet = new HashSet<String>();
            for (QuestionPre pre : questionPreList) {
                typeSet.add(pre.getType());
            }
            //从列表中获取难度集合
            Set levelSet = new HashSet<String>();
            for (QuestionPre pre : questionPreList) {
                levelSet.add(pre.getLevel());
            }
            //获取该类型的总数
            int total = questionService.getTotalWithCondition(condition);
            condition.setTotal(total);
            //页面数据模型
            PageModel model = new PageModel();
            Map data = new HashMap<String, Object>();
            data.put("list",questionPreList);
            data.put("types",typeSet);
            data.put("levels",levelSet);
            data.put("totalWithUser",totalWithUser);
            model.setData(data);
            model.setCondition(condition);
            return em.success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return em.fail("系统异常，请稍后再试");
        }
    }
}
