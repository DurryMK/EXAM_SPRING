package com.des.client.utils.commonUtils;

import com.des.client.serviceImpl.common.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class AesCodeUtil {

    @Autowired
    private CommonServiceImpl commonService;

    //密钥 (需要前端和后端保持一致)十六位作为密钥
    private String KEY = "";

    //密钥偏移量 (需要前端和后端保持一致)十六位作为密钥偏移量
    private String VI = "";

    //算法
    private final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";

    //SSO的TOKEN
    private final String token = "4AaWABnPB5/1Z2EIEXwSfigudpiM4kzLkmIrKQPmS9w=";

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    private byte[] base64Decode(String base64Code) throws Exception {
        /**sun.misc.BASE64Decoder是java内部类，有时候会报错，
         * 用org.apache.commons.codec.binary.Base64替代，效果一样。
         */
        //Base64 base64 = new Base64();
        //byte[] bytes = base64.decodeBase64(new String(base64Code).getBytes());
        //new BASE64Decoder().decodeBuffer(base64Code);
        return StringUtils.isEmpty(base64Code) ? null : new Base64().decodeBase64(new String(base64Code).getBytes());
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @return 解密后的String
     * @throws Exception
     */
    private String aesDecryptByBytes(byte[] encryptBytes) throws Exception {

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);

        byte[] temp = VI.getBytes("UTF-8");
        IvParameterSpec iv = new IvParameterSpec(temp);

        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"), iv);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @return 解密后的string
     * @throws Exception
     */
    public String aesDecrypt(String encryptStr) throws Exception {
        if (StringUtils.isEmpty(encryptStr))
            return null;
        try {
            Map map = commonService.genAesToken();
            KEY = map.get("key") + "";
            VI = map.get("vi") + "";
            return aesDecryptByBytes(base64Decode(encryptStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}