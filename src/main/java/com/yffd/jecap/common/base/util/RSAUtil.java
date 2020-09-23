package com.yffd.jecap.common.base.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA算法：非对称加密算法
 * 加密与解密的秘钥是不同的，速度较慢
 * 使用方式：公钥加密-私钥解密、私钥加密-公钥解密
 */
@Slf4j
public class RSAUtil {
    private static final String ALGORITHM_NAME = "RSA";
    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 2048;
    private static final String PUBLIC_KEY="pub_key";
    private static final String PRIVATE_KEY="pri_key";

    /**
     * 生成密钥对：密钥对中包含公钥和私钥
     * @return  包含 RSA 公钥与私钥的 KeyPair对象
     */
    public static Map<String, Key> initKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
            SecureRandom secureRandom = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            Map<String, Key> keyMap = new HashMap<>();
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;
        } catch (Exception e) {
            log.error("RAS密钥对生成错误", e);
        }
        return null;
    }

    /**
     * 公钥加密
     * @param data 待加密的数据
     * @param key 加密所需的公钥
     * @return 加密后的字节数组
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) {
        try {
            //取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error("RSA公钥加密错误", e);
        }
        return null;
    }

    /**
     * ##私钥加密
     * @param data 待加密的内容
     * @param key 加密所需的公钥
     * @return 加密后的字节数组
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) {
        try {
            //取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            //数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error("RSA私钥加密错误", e);
        }
        return null;
    }

    /**
     * ##公钥解密
     * @param data   待解密的内容
     * @param key    解密需要的私钥
     * @return 解密后的字节数组
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) {
        try {
            //取得公钥
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
            PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
            //数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error("RSA公钥解密错误", e);
        }
        return null;
    }

    /**
     * 私钥解密
     * @param data   待解密的内容 byte[]
     * @param key    解密需要的私钥对象 PrivateKey
     * @return 解密后的字节数组 byte[]
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) {
        try {
            //取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            //数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            log.error("RSA私钥解密错误", e);
        }
        return null;
    }


    /**
     * 加密
     * @param data          待加密的内容
     * @param rsakey        加密密钥
     * @param isPublicKey   是否公钥解密
     * @return
     */
    public static String encrypt(byte[] data, byte[] rsakey, boolean isPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            if (isPublicKey) {
                cipher.init(Cipher.ENCRYPT_MODE, RSAUtil.parsePublicKey(rsakey));
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, RSAUtil.parsePrivatekey(rsakey));
            }
            byte[] bytes = cipher.doFinal(data);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            log.error("RSA公钥加密错误", e);
        }
        return null;
    }

    /**
     * 解密
     * @param data          待解密的内容
     * @param rsakey        解密密钥
     * @param isPublicKey   是否公钥解密
     * @return
     */
    public static String decrypt(byte[] data, byte[] rsakey, boolean isPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            if (isPublicKey) {
                cipher.init(Cipher.DECRYPT_MODE, RSAUtil.parsePublicKey(rsakey));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, RSAUtil.parsePrivatekey(rsakey));
            }
            byte[] bytes = cipher.doFinal(data);
            return new String(bytes);
        } catch (Exception e) {
            log.error("RSA私钥解密错误", e);
        }
        return null;
    }


    /**
     * 获取公钥
     * @param keyPair
     * @return
     */
    public static byte[] getPublicKey(Map<String, Key> keyPair) {
        Key key = (Key) keyPair.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 获取私钥
     * @param keyPair
     * @return
     */
    public static byte[] getPrivateKey(Map<String, Key> keyPair) {
        Key key = (Key) keyPair.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 公钥转换成 PublicKey 对象
     * @param key
     * @return PublicKey
     */
    public static PublicKey parsePublicKey(byte[] key) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            log.error("转换公钥对象失败", e);
        }
        return null;
    }

    /**
     * 私钥转换成 PrivateKey 对象
     * @param key
     * @return PrivateKey
     */
    public static PrivateKey parsePrivatekey(byte[] key) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            log.error("转换私钥对象失败", e);
        }
        return null;
    }


    public static void main(String[] args) {
        Map<String, Key> keyMap = RSAUtil.initKeyPair();
        byte[] publicKey = RSAUtil.getPublicKey(keyMap);
        byte[] privateKey = RSAUtil.getPrivateKey(keyMap);
        System.out.println("公钥：" + Base64.getEncoder().encodeToString(publicKey));
        System.out.println("私钥：" + Base64.getEncoder().encodeToString(privateKey));

        System.out.println("================密钥对构造完毕，开始进行加密数据的传输=============");
        String str= "aattaggcctegthththfef/aat.mp4";
        System.out.println("===========甲方向乙方发送加密数据==============");
        System.out.println("原文:" + str);
        //甲方进行数据的加密
        byte[] encodeBytes = RSAUtil.encryptByPublicKey(str.getBytes(), publicKey);
        System.out.println("甲方公钥加密数据：" + Base64.getEncoder().encodeToString(encodeBytes));
        //乙方进行数据的解密
        byte[] decodeBytes = RSAUtil.decryptByPrivateKey(encodeBytes, privateKey);
        System.out.println("乙方私钥解密数据：" + new String(decodeBytes) + "");


        System.out.println("===========反向进行操作，乙方向甲方发送数据==============");
        str="乙方向甲方发送数据RSA算法";
        System.out.println("原文:" + str);
        byte[] code2 = RSAUtil.encryptByPrivateKey(str.getBytes(), privateKey);
        System.out.println("乙方私钥加密数据：" + Base64.getEncoder().encodeToString(code2));
        byte[] decode2 = RSAUtil.decryptByPublicKey(code2, publicKey);
        System.out.println("甲方公钥解密数据：" + new String(decode2));
    }
}
