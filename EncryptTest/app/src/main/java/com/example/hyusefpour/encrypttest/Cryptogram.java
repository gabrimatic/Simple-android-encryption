package com.example.hyusefpour.encrypttest;

import java.nio.charset.Charset;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Cryptogram {

    private static byte[] iv;
    private static IvParameterSpec ivspec;

    public static byte[] Encrypt(String txt, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(txt.getBytes("UTF-8"));
            iv = cipher.getIV();
            ivspec = new IvParameterSpec(iv);
            return cipherText;
        } catch (Exception e) {
            return e.toString().getBytes(Charset.forName("UTF-8"));
        }
    }

    public static String Decrypt(byte[] txt, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", Security.getProvider("BC"));
            cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
            String decryptString = new String(cipher.doFinal(txt), "UTF-8");
            return decryptString;
        } catch (Exception e) {
            return e.toString();
        }
    }
}