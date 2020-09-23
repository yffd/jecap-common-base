package com.yffd.jecap.common.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES算法：对称加密
 * 加密与解密的密钥是相同的，加解密速度很快
 * 密钥的长度可以是128位、192位、256位，越大越安全
 */
public class AESUtil {
    private static final Logger log = LoggerFactory.getLogger(AESUtil.class);

    /** 加密算法名称 */
    private static final String ALGORITHM_NAME = "AES";
    private static final int KEY_SIZE = 256;

    /**
     * 加载一个AES的密钥，密钥长度为256位
     * @return 返回经过BASE64加密处理之后的密钥字符串
     */
    public static String loadAESKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM_NAME);
            SecureRandom secureRandom = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());
            keyGen.init(KEY_SIZE, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            log.error("AES加密算法不存在", e);
        }
        return null;
    }

    /**
     * 将生成的AES密钥（BASE64加密后），转为 SecretKey对象
     * @param aesKey
     * @return
     */
    public static SecretKey aesKey2SecretKey(String aesKey) {
        byte[] bytes = Base64.getDecoder().decode(aesKey);
        SecretKeySpec secretKey = new SecretKeySpec(bytes, ALGORITHM_NAME);
        return secretKey;
    }

    /**
     * AES加密
     * @param content   待加密内容
     * @param secretKey 加密使用的 AES 密钥
     * @return 加密后的密文
     */
    public static byte[] encryptAES(byte[] content, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(content);
        } catch (Exception e) {
            log.error("AES加密错误", e);
        }
        return null;
    }

    /**
     * AES解密
     * @param content   待解密内容
     * @param secretKey 解密使用的 AES 密钥
     * @return  解密后的明文
     */
    public static byte[] decryptAES(byte[] content, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(content);
        } catch (Exception e) {
            log.error("AES解密错误", e);
        }
        return null;
    }

    /**
     * AES加密
     * @param text   待加密内容
     * @param aesTextKey    加密使用的 AES 密钥
     * @return AES加密后，并经过BASE64加密处理后的密文
     */
    public static String encryptAES(String text, String aesTextKey) {
        try {
            SecretKey secretKey = AESUtil.aesKey2SecretKey(aesTextKey);
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypt = cipher.doFinal(text.getBytes());
            return Base64.getEncoder().encodeToString(encrypt);
        } catch (Exception e) {
            log.error("AES加密错误", e);
        }
        return null;
    }

    /**
     * AES解密
     * @param text   待解密内容，该内容是经过BASE64加密处理过的
     * @param aesTextKey    解密使用的 AES 密钥
     * @return  解密后的明文
     */
    public static String decryptAES(String text, String aesTextKey) {
        try {
            byte[] content = Base64.getDecoder().decode(text);
            SecretKey secretKey = AESUtil.aesKey2SecretKey(aesTextKey);
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypt = cipher.doFinal(content);
            return new String(decrypt);
        } catch (Exception e) {
            log.error("AES加密错误", e);
        }
        return null;
    }

}
