package com.gahui.ghmall.server.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * @description: AES加密解密工具类
 * @author: Gahui
 * @since: 2021/3/16
 **/
public class AesHelper {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密解密算法 / 工作模式 / 填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param aesKey 秘钥 -- 需要确保base64转码后为16位字节
     * @param data   需要加密的数据
     * @return 加密后的字符串
     * @throws Exception gg
     */
    public static String encrypt(String aesKey, String data) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(aesKey.getBytes(StandardCharsets.UTF_8));
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = encrypt(dataBytes, keyBytes);
        return new String(Base64.encodeBase64(encryptedBytes), StandardCharsets.UTF_8);

    }

    /**
     * 解密
     *
     * @param aesKey 秘钥 -- 需要确保base64转码后为16位字节
     *               详见：http://www.ruanyifeng.com/blog/2008/06/base64.html
     * @param data   需要解密的数据
     * @return 解密后的字符串
     * @throws Exception gg
     */
    public static String decrypt(String aesKey, String data) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(aesKey.getBytes(StandardCharsets.UTF_8));
        byte[] encryptedDataBytes = Base64.decodeBase64(data.getBytes(StandardCharsets.UTF_8));
        byte[] dataBytes = decrypt(encryptedDataBytes, keyBytes);
        return new String(dataBytes, StandardCharsets.UTF_8);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     * @throws Exception gg
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = new SecretKeySpec(key, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     * @throws Exception gg
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = new SecretKeySpec(key, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }
}
