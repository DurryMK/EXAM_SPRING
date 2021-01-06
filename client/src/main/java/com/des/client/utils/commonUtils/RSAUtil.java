package com.des.client.utils.commonUtils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class RSAUtil {
    public static final String PUBLIC = "PUBLIC";
    public static final String PRIVATE = "PRIVATE";

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static Map genKeyPair() throws NoSuchAlgorithmException {
        Map<String, String> keyMap = new HashMap();
        Random rd = new Random();
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小600 - 1000随机的长度
        keyPairGen.initialize(rd.nextInt(400)+600,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(PUBLIC,publicKeyString);  //公钥
        keyMap.put(PRIVATE,privateKeyString);  //私钥
        return keyMap;
    }
    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public static void main(String[] args) throws Exception {
        String str = "MGgwDQYJKoZIhvcNAQEBBQADVwAwVAJNE5ll48hOHtFJ+dEiJZT1LTuVbjfrin2i3RM+Y8rQqjv6QuKXth7BYLDBcrHQTyD4xtwNglhP0YHg8V69/fJPvrbtrb05uM5OdcdzPt0CAwEAAQ==";
        String encrypt = RSAUtil.encrypt("12312312321;qwdsdasd-asd-asda-da", str);
        System.out.println(encrypt);
        String decrypt = RSAUtil.decrypt(encrypt, "MIIBjQIBADANBgkqhkiG9w0BAQEFAASCAXcwggFzAgEAAk0TmWXjyE4e0Un50SIllPUtO5VuN+uKfaLdEz5jytCqO/pC4pe2HsFgsMFysdBPIPjG3A2CWE/RgeDxXr398k++tu2tvTm4zk51x3M+3QIDAQABAk0FT8BGQAWz4mJa+qt3ZewgKxKvQSZrbOsTYOC6qL9uMzz6nXAiIibRYCnyET7k9kGphq6eTXmBJOdFZQuPSOhapQ/rDYIgJT0aGrrbvQInB/RJKXD3NQQMQJfujL+NaCCoV9HqC9frtQz/2npZIm/DuwjY9+0fAicCdshgYZ6wIeJ6o+6yfPFkY22sM4eI/jG8J5VljvRW+DgPx/hmGIMCJwLdzHOrqtonEEdCuQttiAbLJf8nvUDxWZaQDa9swaxAPHetM4r8nwInAKzaNgcqPCvc6yyXEZRJdfAFMsYgIvs9V8klGGJ3NInqmvB6ckfPAicEqebAoA76jK0zBZM6Vy8XL0Lw5OM6PGMFfFXAU7eDUmiksYqzuVU=");
        System.out.println(decrypt);
    }

}

