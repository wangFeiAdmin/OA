package com.wf.oa.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Utils {
    private MD5Utils(){}

    /**
     * 用于对当前密码进行加密
     * @param pas
     * @return
     * @throws Exception
     */
    public static String  getEncryptPassword(String  pas)  {
        MessageDigest md5 = null;
        BASE64Encoder base64en = new BASE64Encoder();
        String encode=null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            encode = base64en.encode(md5.digest(pas.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //加密后的字符串
        return encode;
    }

    /**
     * 用于验证当前加密后的密码，和之前的密码是否一致
     * @param oldpas
     * @param newPas
     * @return
     * @throws Exception
     */
    public static boolean verifyPassword(String oldpas,String newPas){
        return  oldpas.equals(getEncryptPassword(newPas));
    }


}
