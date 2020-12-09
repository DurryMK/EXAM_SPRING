package com.des.client.controller.component.question;

import com.ctc.wstx.util.StringUtil;
import com.des.client.conf.ResConst;
import com.des.client.entity.ListInPage;
import com.des.client.entity.question.condition.QueListQueryCondition;
import com.des.client.serviceImpl.question.QuestionServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/que")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    /**
     * 初始化系统题目列表页面
     */
    @RequestMapping("/initPage")
    public Map initPage(HttpServletRequest request, ListInPage listInPage, Map map, QueListQueryCondition condition, HttpServletResponse response) {
        map = new HashMap<String, Object>();
        //1.session中是否有查询记录
        Object listInSession = request.getSession().getAttribute("listInPage");
        if (listInSession != null) {
            //2.有查询记录，则使用该查询条件继续查询
            listInPage = (ListInPage) listInSession;
        } else {
            //3.没有 则赋初值
            listInPage.setCurrentPage(1);
            listInPage.setPageSize(10);
            listInPage.setTotal(0);
        }
        try {
            condition.setOwner("0");// 0 查询系统题库
            //4.查询单页数据
            map = questionService.queryQuestionListInfoByPage(condition, listInPage);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
            map.put(ResConst.RESINFO, "获取数据异常");
        }
        return map;
    }

    @RequestMapping("/genQuestionList")
    public Map quickLogin(HttpServletRequest request, ListInPage listInPage, Map map, HttpServletResponse response) {
        map = new HashMap<String, Object>();
        try {
            //1.获取查询参数
            String param = request.getParameter("param");
            //2.解码
            param = URLDecoder.decode(param, "UTF-8");
            //3.解析 pageSizes[0]=5&pageSizes[1]=10&pageSizes[2]=15&pageSizes[3]=20&pageSizes[4]=25&pageSize=10&total=0&currentPage=1&searchKey=
            listInPage = parse(param, listInPage);
            //4.查询单页数据
            map = questionService.queryQuestionListInfoByPage(new QueListQueryCondition(), listInPage);


        } catch (Exception e) {
            e.printStackTrace();
            map.put(ResConst.RESTOKEN, ResConst.SUCCESS);
            map.put(ResConst.RESINFO, "获取数据异常");
        }
        return map;
    }

    private ListInPage parse(String param, ListInPage listInPage) {
        String[] params = param.split("&");
        for (String s : params) {
            String name = null;
            String value = null;
            try {
                name = s.split("=")[0];
                value = s.split("=")[1];
            } catch (Exception e) {
                continue;
            }
            if ("pageSize".equals(name) && value != "" && StringUtils.isNotEmpty(value)) {
                //当前分页数
                listInPage.setPageSize(Integer.parseInt(value));
            }
            if ("currentPage".equals(name) && value != "" && StringUtils.isNotEmpty(value)) {
                //当前分页数
                listInPage.setCurrentPage(Integer.parseInt(value));
            }
            if ("searchKey".equals(name) && value != "" && StringUtils.isNotEmpty(value)) {
                //当前分页数
            }
        }
        return listInPage;
    }
}
