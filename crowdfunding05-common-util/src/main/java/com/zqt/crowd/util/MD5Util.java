package com.zqt.crowd.util;

import com.zqt.crowd.constant.CommonConstant;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: zqtao
 * @description: MD5 明文字符串MD5加密工具
 * @Date: 2020/5/21 20:14
 * @version: 1.0
 * @Deprecated 废弃使用，密码加密，使用的是spring security 提供的 BCryptPasswordEncoder 进行盐值加密
 */
@Deprecated
public class MD5Util {
    /**
     * 对铭文字符串进行MD5加密
     *
     * @param source 传入的明文字符串
     * @return 加密结果密文
     */
    public static String md5(String source) {

        // 1、判断有效性
        if (source == null || source.length() == 0) {
            // 2、无效，抛异常
            throw new RuntimeException(CommonConstant.MESSAGE_STRING_INVALIDATE);
        }
        try {
            // 3、获取MessageDigest对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4、获取明文字符串字节数组
            byte[] input = source.getBytes();

            // 5、加密
            byte[] output = messageDigest.digest(input);

            // 6、创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7、按照16进制将bigInteger的值转换为字符串
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
