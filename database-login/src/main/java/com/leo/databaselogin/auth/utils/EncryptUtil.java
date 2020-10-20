package com.leo.databaselogin.auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 字符串加密，校验工具类
 */
public class EncryptUtil implements PasswordEncoder {
    private static final String SITE_WIDE_SECRET = "XXXXXXXXXXXX";
    //private static final PasswordEncoder encoder = new StandardPasswordEncoder(SITE_WIDE_SECRET);
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 加密方法
     * @param rawPassword 被加密的字符串
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 比较是否相等
     * @param rawPassword 加密后的字符串
     * @param password 源字符串
     * @return
     */
    @Override
    public boolean matches(CharSequence password, String rawPassword) {
        boolean isSuccess = false;
        try {
            isSuccess = encoder.matches(password, rawPassword);
        } catch(Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    public static void main(String[] args) {
        EncryptUtil util = new EncryptUtil();
        /*String source = "1";
        String target = "6900da5dfcf294bf5a6a48ad9579b007ab87e0af91f3982b5862e3def63ef8c1eff60e70243e4cbd";
        System.out.println(util.matches(source, target));*/
        System.out.println(util.encode("123456"));
    }

}
