package com.medical.exam.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
 * mini program utils
 */
public class MiniProgramUtils {
    private static boolean hasInited = false;

    public static JSONObject decrypt(String session_key, String iv, String encryptData) {
        String decryptString = "";
        init();
        byte[] sessionKeyByte = Base64.decodeBase64(session_key);
        byte[] ivByte = Base64.decodeBase64(iv);
        byte[] encryptDataByte = Base64.decodeBase64(encryptData);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key key = new SecretKeySpec(sessionKeyByte, "AES");
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
            algorithmParameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
            byte[] bytes = cipher.doFinal(encryptDataByte);
            decryptString = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.parseObject(decryptString);
    }

    private static void init() {
        if (hasInited) {
            return;
        }
        Security.addProvider(new BouncyCastleProvider());
        hasInited = true;
    }


}