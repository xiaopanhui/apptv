package com.sf.appTv.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class Utils {
    /*CBC加密*/
    private static final String k = "testtest";
    private static final String iv = "12345678";
    private final static ObjectMapper mapper = new ObjectMapper();

    public static String encryptDESK(String encryptString) {
        String keyParam = k;    //   keyParam 密钥
//        if (StringUtils.isEmpty(keyParam)) keyParam = k;
        IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
        SecretKeySpec key = new SecretKeySpec(keyParam.getBytes(), "DES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
//        三个参数：第一个参数表示是加密模式，第二个参数是密钥key，第三个参数是加密算法IvParameterSpec
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        byte[] encryptedData = new byte[0];
        try {
            encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.encode(encryptedData).replaceAll(" ", "");
    }
    //解密

    /**
     * DES解密（传入key）
     *
     * @param decryptString 待解密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static String decryptDESK(String decryptString) {
        String s = new String(decryptString.replaceAll(" ", "+"));
        String keyParam = k;
        byte[] byteMi = Base64.decode(s);
        IvParameterSpec zeroIv = null;
        SecretKeySpec key = null;
        try {
            zeroIv = new IvParameterSpec(iv.getBytes("UTF-8"));
            key = new SecretKeySpec(keyParam.getBytes("UTF-8"), "DES");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        byte decryptedData[] = new byte[0];
        try {
            decryptedData = cipher.doFinal(byteMi);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        String kk = null;
        try {
            kk = new String(decryptedData, "utf-8").replaceAll("&quot;", "\"").replaceAll("\r\n","").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("解密的结果为" + kk);
//        return new String(decryptedData, "utf-8");
        return kk;
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    public static String base64Decode1(String base64Code) throws Exception {
        String s = StringUtils.isEmpty(base64Code) ? null : new BASE64Encoder().encode(base64Code.getBytes());
        return s;
    }


    /**
     * DES解密
     *
     * @param decryptString 待解密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static String decryptDES(String decryptString) throws Exception {

        JSONObject json = new JSONObject(decryptDESK(decryptString));
        StringUtils.isEmpty(json.optString("data", ""));
        System.out.println(json.toString());
        return json.toString();
//        String dataValue = decryptDESK(json.optString("data", ""));
//        try {
//            JSONObject dataJson = new JSONObject(dataValue);
//            json.put("data", dataJson);
//        } catch (JSONException e) {
//        }
//        try {
//            JSONArray dataJson = new JSONArray(dataValue);
//            json.put("data", dataJson);
//        } catch (JSONException e) {
//        }
//        return json.toString();
    }


    public static List<Map<String, Object>> getMap(MultipartFile file) {
        List<Map<String, Object>> res = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        if (!fileName.contains("xls") && !fileName.contains("xlsx")) {
        } else {
            Workbook wb = null;
            try {
                wb = WorkbookFactory.create(file.getInputStream());
                Sheet sheet = wb.getSheetAt(0);
                Row row = sheet.getRow(sheet.getFirstRowNum());
                List<String> list = new ArrayList<>();
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = (Cell) cells.next();
                    cell.setCellType(CellType.STRING);
                    list.add(cell.getStringCellValue());
                }
                for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    Row row1 = sheet.getRow(i);
                    Iterator cells1 = row1.cellIterator();
                    int flag = 0;
                    while (cells1.hasNext()) {
                        Cell cell = (Cell) cells1.next();
                        cell.setCellType(CellType.STRING);
                        map.put(list.get(flag), cell.getStringCellValue());
                        flag++;
                    }
                    res.add(map);
                }
            } catch (IOException e) {
            }
        }
        return res;
    }

//    public static void main(String[] args) throws Exception {
//
//
////        String jiemi="{\n" +
////                " \"data\": \"f1Oj+DvZHxBI7QERae0fUfCGq9EoYi6Iijt8AYCk/+ArjtQQdjiLurK1v22BGPzTcREVDlIxPuRcmx33d6kUoMav5az93shsPRTspRKP4/Bg87Gj7/N7FKixtO6d3p+O5QVv8ByqjKRL/rX1Wp80RuNkCEnEnkGIVq5bsjn+DiE=\"\n" +
////                "}";
////        String enString="\"ret\":0,\"msg\":\"Ok\",\"data\":{\"username\":1001,\"vipState\":1,\"avatar\":\"http://www.bejson.com/1.jpg\",\"expireDate\":\"2019-11-19T04:50:30.000+0000\",\"selectId\":2}";
////        String jiemi=  Utils.encryptDESK(enString);
////        Utils.encryptDESK(jiemi);
//        String jiemi = "dcAAvXNyP7zWDtLW9riP3iphRd0+tUaSQt3iJXfyg0qNRAcNgiY8trkNdquv+QdeUTSbLfeO+craGINeGlAeFKfw6sTdVfeSJDNJ8rkncZWYpqJWwyK05u/zRahcYNIs6vJ8dQB+mzprj/EIORmxaXNWKZ3XGj5uYHhj+gGjy7nfqrPe1S/GtS//ZSrne6yTWcgtA013nBtVB5fY7A/keooRRUnBJSvqf8mPgJcQUgOdzteAFmbKLs9tZC7Z4OZ6b2YlyltvRxXenl9wQFywei461xGxVjLihG7ZEm2ZsFmI5Ox4AsXS94XnC43tMtqRcbELNjPCfIbtL/YI/CQP00IOTUv6WEFFYPWWElWwAa4=";
////        String jiemi1 = "sARVZEgfig9Fak8xd56MsrIduo1D6WC4hG6rDuaNGpvbAvwBRaV33NvuLeKHkMEonJZve0e1Q1oOioF3C8og/OmSiaMhAtp+Ivt+W0vmS460ePeINldg0IRWPv0xuLRigf/8fVu0/8ca2+8tYrfdFZbgw0CvdKrd";
////  Utils.decryptDES(jiemi);
//        String jiemi1 = "MBukfoxAb8JuGqaMrp8TuzyqLEAGf4C+6AP6h7lLK+jzVJaCn4fH5GXlgF44Gs1qhCQRExwcvcYwW79kdu0PKojHddgiekBlbI1JeN31ZzFN8hdDu0s8QA==";
//        String yan="{\"id\": 1,\"dwonloadUrl\": \"http://192.168.131.108:4000/upload/apk/com.example.tv01.apk\"}";
//        System.out.println( Utils.encryptDESK( yan.trim()));
//        String s = "MBukfoxAb8JuGqaMrp8TuzyqLEAGf4C+6AP6h7lLK+jzVJaCn4fH5GXlgF44Gs1qhCQRExwcvcYwW79kdu0PKojHddgiekBlbI1JeN31ZzFN8hdDu0s8QA==";
//
//        String o= Utils. decryptDESK(s);
//        System.out.println("jiemi1=========================="+o);
//
//        String str ="这是要加密的字符串,使用jdk";
//
//
//
//
//
//    }
}
