package com.des.client.interfaceUtils.iBaidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.des.client.conf.ResConst;
import com.des.client.interfaceUtils.iBaidu.baiduPackage.BaiduInterfaceURL;
import com.des.client.interfaceUtils.iBaidu.baiduPackage.GsonUtils;
import com.des.client.interfaceUtils.iBaidu.baiduPackage.HttpUtil;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用百度人脸识别接口的工具
 */
@Component
public class FaceDetectionUtil {

    @Autowired
    private CommonServiceImpl commonService;

    public Map faceDetect(String imgStr) {
        Map resMap = new HashMap<String, String>();
        // 请求url
        String url = BaiduInterfaceURL.Baibu_Detection;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imgStr);//图片的BASE64编码
            map.put("face_field", "quality");//quality用于识别图片质量是否符合人脸识别的要求
            map.put("image_type", "BASE64");//图片格式BASE64

            String param = GsonUtils.toJson(map);//转为JOSN格式

            // 获取接口token
            Map tokenMap = commonService.genBaiduToken();
            if (!tokenMap.get(ResConst.RESTOKEN).equals(ResConst.SUCCESS)) {
                resMap.put(ResConst.RESINFO, "图像采集失败");
                resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
                return resMap;
            }
            String accessToken = tokenMap.get(ResConst.BAIDUTOKEN) + "";
            //调用接口获取返回值
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            //解析结果返回
            return parse(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map parse(String result) {
        //处理接口返回的数据
        Map resMap = new HashMap<String, String>();
        //JSON解析
        JSONObject jsonObject = JSON.parseObject(result);
        //是否成功的标识 SUCCESS
        String error_msg = jsonObject.getString("error_msg");
        if (StringUtils.isEmpty(error_msg) || !error_msg.equals("SUCCESS")) {
            //返回结果失败
            resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
            return resMap;
        }
        //检测结果
        JSONObject resultJson = jsonObject.getJSONObject("result");
        //人脸数量
        double face_num = Double.parseDouble(resultJson.getString("face_num"));
        if (face_num == 0) {
            resMap.put(ResConst.RESINFO, "未检测到人脸");
            resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
            return resMap;
        } else if (face_num > 1) {
            resMap.put(ResConst.RESINFO, "人数过多");
            resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
            return resMap;
        } else {
            /**
             *left_eye : 0.6, #左眼被遮挡的阈值
             * right_eye : 0.6, #右眼被遮挡的阈值
             * nose : 0.7, #鼻子被遮挡的阈值
             * mouth : 0.7, #嘴巴被遮挡的阈值
             * left_cheek : 0.8, #左脸颊被遮挡的阈值
             * right_cheek : 0.8, #右脸颊被遮挡的阈值
             * chin_contour : 0.6, #下巴被遮挡阈值
             * blur : 取值范围[0~1]，0是最清晰，1是最模糊	小于0.7
             * illumination : 取值范围[0~255],脸部光照的灰度值，0表示光照不好,以及对应客户端SDK中，YUV的Y分量 大于40
             * completeness :（0或1），0为人脸溢出图像边界，1为人脸都在图像边界内
             * */
            //照片质量评估
            JSONObject quality = resultJson.getJSONArray("face_list").getJSONObject(0).getJSONObject("quality");
            //光照程度
            double illumination = quality.getDouble("illumination");
            //模糊程度
            double blur = quality.getDouble("blur");
            //完整程度
            double completeness = quality.getDouble("completeness");
            //五官遮挡程度
            JSONObject occlusion = quality.getJSONObject("occlusion");

            if (illumination < 30) {
                resMap.put(ResConst.RESINFO, "光线过暗");
                resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
                return resMap;
            } else if (completeness != 1) {
                resMap.put(ResConst.RESINFO, "请保持面部在采集框范围内");
                resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
                return resMap;
            } else if (blur < 0.6) {
                resMap.put(ResConst.RESINFO, "请保持图像稳定");
                resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
                return resMap;
            } else if (occlusion.getDouble("right_eye") > 0.6 || occlusion.getDouble("left_eye") > 0.6 ||
                    occlusion.getDouble("nose") > 0.7 || occlusion.getDouble("mouth") > 0.7 ||
                    occlusion.getDouble("left_cheek") > 0.8 || occlusion.getDouble("right_cheek") > 0.8 ||
                    occlusion.getDouble("chin_contour") > 0.6) {
                resMap.put(ResConst.RESINFO, "请保持面部清晰无遮挡");
                resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
                return resMap;
            }
            resMap.put(ResConst.RESINFO, "采集成功");
            resMap.put(ResConst.RESTOKEN, ResConst.SUCCESS);
        }
        return resMap;
    }

    public static void main(String[] args) {
        System.out.println(parse(""));
    }
}
