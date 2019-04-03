package com.sf.appTv.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密
 */
public class DESCoder1 {

    /**
     * 加密0向量
     */
    private static final String k = "testtest";
    private static final String iv = "12345678";

    /**
     * des加密
     *
     * @param encryptString 需要加密的字符串
     * @param keyParam      密钥
     * @return 加密后的密文
     */
    public static String encryptDESK(String encryptString, String keyParam) throws Exception {
        if (StringUtils.isEmpty(keyParam))
            keyParam = k;
        IvParameterSpec zeroIv = new IvParameterSpec(keyParam.getBytes());
        SecretKeySpec key = new SecretKeySpec(keyParam.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
        return Base64.encode(encryptedData).replaceAll(" ", "");
    }

    /**
     * DES解密（传入key）
     *
     * @param decryptString 待解密的数据

     * @return 解密后的明文
     * @throws Exception
     */
    public static String decryptDESK(String decryptString) throws Exception {

            String keyParam = k;
        byte[] byteMi = Base64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec key = new SecretKeySpec(keyParam.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData, "utf-8");
    }

    /**
     * DES解密
     *
     * @param decryptString 待解密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static String decryptDES(String decryptString) throws Exception {
         String key=k;
        JSONObject json = new JSONObject(decryptDESK(decryptString));
        if (StringUtils.isEmpty(json.optString("data", "")))
            return json.toString();
        String dataValue = decryptDESK(json.optString("data", ""));
        try {
            JSONObject dataJson = new JSONObject(dataValue);
            json.put("data", dataJson);
        } catch (JSONException e) {
        }
        try {
            JSONArray dataJson = new JSONArray(dataValue);
            json.put("data", dataJson);
        } catch (JSONException e) {
        }
        return json.toString();
    }

    public static void main(String[] args) throws Exception {
        String s="MBukfoxAb8JuGqaMrp8TuzyqLEAGf4C+6AP6h7lLK+jzVJaCn4fH5GXlgF44Gs1qhCQRExwcvcYwW79kdu0PKojHddgiekBlbI1JeN31ZzFN8hdDu0s8QA==";
        System.out.println();  DESCoder1.decryptDES(s);
    }
}
