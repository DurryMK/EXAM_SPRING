package com.des.client.controller.Interface;

import com.des.client.conf.ResConst;
import com.des.client.interfaceUtils.iBaidu.FaceCompareUtil;
import com.des.client.interfaceUtils.iBaidu.FaceDetectionUtil;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.utils.commonUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/interface")
public class InterfaceController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CommonServiceImpl commonService;
    @Autowired
    private FaceDetectionUtil faceDetectionUtil;//百度人脸检测工具
    @Autowired
    private FaceCompareUtil faceCompareUtil;//百度人脸对比工具


    private static final String DetectionToken = "DcToken_";

    /**
     * 人脸检测
     */
    @RequestMapping("/faceDetectionOp")
    @ResponseBody
    public Map faceDetectionOp(HttpServletRequest request) {
        Map resMap;
        //接收前端传来的图片  注：因为图片传到后台是base64格式 数据过大 需要配置 http请求头的最大长度限制
        String imgStr = request.getParameter("imgStr");
        String paperId = request.getParameter("paperId");
        String examineeId = request.getParameter("examineeId");
        String runningId = request.getParameter("runningId");
        resMap = faceDetectionUtil.faceDetect(imgStr);
        if (resMap.get(ResConst.RESTOKEN).equals(ResConst.SUCCESS)) {
            //采集成功 缓存图片信息到redis 以固定token+试卷编号+考生编号+每场考试的随机id作为key
            redisUtil.set(DetectionToken + paperId + examineeId + runningId, imgStr);
            System.out.println("人脸检测token" + DetectionToken + paperId + examineeId + runningId);
        }
        return resMap;
    }

    /**
     * 人脸对比
     */
    @RequestMapping("/faceCompareOp")
    @ResponseBody
    public Map faceCompareOp(HttpServletRequest request) {
        Map resMap = new HashMap<String, Object>();
        //接收前端传来的图片  注：因为图片传到后台是base64格式 数据过大 需要配置 http请求头的最大长度限制
        String imgStr = request.getParameter("imgStr");
        String paperId = request.getParameter("paperId");
        String examineeId = request.getParameter("examineeId");
        String runningId = request.getParameter("runningId");

        try {
            //从redis取出要对比的图片
            String targetImg = redisUtil.get(DetectionToken + paperId + examineeId + runningId);
            resMap = faceCompareUtil.faceMatch(imgStr, targetImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }
}
