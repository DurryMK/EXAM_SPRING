package com.des.client.interfaceUtils.iBaidu;

import com.des.client.conf.ResConst;
import com.des.client.interfaceUtils.iBaidu.baiduPackage.BaiduInterfaceURL;
import com.des.client.interfaceUtils.iBaidu.baiduPackage.GsonUtils;
import com.des.client.interfaceUtils.iBaidu.baiduPackage.HttpUtil;
import com.des.client.serviceImpl.common.CommonServiceImpl;
import com.des.client.utils.commonUtils.Image2Base64;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用百度人脸对比接口
 * 2020-11-25 16：27
 */
@Component
public class FaceCompareUtil {
    /**
     * api请求可选参数:
     * image	是	string	图片信息(总数据大小应小于10M)，图片上传方式根据image_type来判断。 两张图片通过json格式上传，格式参考表格下方示例
     * ---------
     * image_type	是	string	图片类型
     * BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；
     * URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
     * FACE_TOKEN: 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
     * ---------
     * face_type	否	string	人脸的类型
     * LIVE：表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等，
     * IDCARD：表示身份证芯片照：二代身份证内置芯片中的人像照片，
     * WATERMARK：表示带水印证件照：一般为带水印的小图，如公安网小图
     * CERT：表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片
     * INFRARED 表示红外照片：使用红外相机拍摄的照片
     * 默认LIVE
     * ----------
     * quality_control	否	string	图片质量控制
     * NONE: 不进行控制
     * LOW:较低的质量要求
     * NORMAL: 一般的质量要求
     * HIGH: 较高的质量要求
     * 默认 NONE
     * -----------
     * 若图片质量不满足要求，则返回结果中会提示质量检测失败
     * liveness_control	否	string	活体检测控制
     * NONE: 不进行控制
     * LOW:较低的活体要求(高通过率 低攻击拒绝率)
     * NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)
     * HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
     * 默认 NONE
     * 若活体检测结果不满足要求，则返回结果中会提示活体检测失败
     */
    @Autowired
    private CommonServiceImpl commonService;

    public Map faceMatch(String img1, String img2) {
        // 请求url
        String url = BaiduInterfaceURL.Baibu_FaceCompare;
        try {
            List<Map> params = new ArrayList<Map>();
            //api参数
            Map map1 = new HashMap<String, String>();
            Map map2 = new HashMap<String, String>();
            map1.put("image", img1);
            map1.put("image_type", "BASE64");
            map2.put("image", img2);
            map2.put("image_type", "BASE64");
            params.add(map1);
            params.add(map2);
            //转json格式
            String param = GsonUtils.toJson(params);
            System.out.println(param);
            //获取api token
            String accessToken = GenBaiduToken.getAuth();
            //调用接口
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            //对返回结果进行处理
            return parse(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map parse(String result) {
        //处理接口返回的数据
        Map resMap = new HashMap<String,String>();
        Map apiMap = GsonUtils.fromJson(result, Map.class);
        String flag = apiMap.get("error_msg") + "";
        if (StringUtils.isEmpty(flag) || !flag.equals("SUCCESS")) {
            //返回结果失败
            resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
            return resMap;
        }
        //获取照片对比结果
        apiMap = (Map)apiMap.get("result");
        //照片对比得分
        double score = Double.parseDouble(apiMap.get("score")+"");
        if(score>75){
            //以75作为阈值
            resMap.put(ResConst.RESTOKEN, ResConst.SUCCESS);
        }else{
            //以75作为阈值
            resMap.put(ResConst.RESTOKEN, ResConst.FAIL);
        }
        return resMap;
    }

}
