package com.example.learn.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegexUtils
 * @Description 验证信息工具类
 * @Date 2022/11/17 11:20
 * @Author pluto
 */
public class RegexUtils {

    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex, idCard);
    }

    /**
     * 验证手机号码 1开头11位
     *
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        String regex = "^(1)\\d{10}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * 大陆手机号格式校验
     * @param phone
     * @return
     */
    public static boolean isChinaPhone(String phone) {
        String regExp = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[235-8]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[0-35-9]\\d{2}|66\\d{2})\\d{6}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }


    /**
     * 登陆用户名校验
     *
     * @param username
     * @return
     */
    public static boolean checkUserName(String username) {
        String regex = "^[a-z0-9_-]{4,20}$";
        return Pattern.matches(regex, username);
    }

    /**
     * 登陆密码校验
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        String regex = "(?!^[0-9]+$)(?!^[a-z]+$)(?!^[A-Z]+$)(?!^[^A-z0-9]+$)^[^\\s\\u4e00-\\u9fa5]{8,}$";
        return Pattern.matches(regex, password);
    }


    /**
     * @param mobile
     * @Description 手机号脱敏
     * @Date 2022/6/13 2:08 下午
     * @Author pluto
     * @Return java.lang.String
     */
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return mobile;
        }
        String trim = mobile.trim();
        if (trim.length() != 11) {
            return mobile;
        }
        return trim.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }


    public static String nameDesensitization(String str) {
        if(StringUtils.isEmpty(str)) {
            return str;
        }
        if (isNumeric(str) && str.length() == 11) {
            return mobileEncrypt(str);
        }
        char[] sArr = str.toCharArray();
        if (sArr.length == 1) {
            return "*";
        } else if (sArr.length == 2) {
            return sArr[0] + "*";
        } else if (sArr.length  > 2) {
            for (int i = 1; i < sArr.length -1; i++) {
                sArr[i] = '*';
            }
            return new String(sArr);
        }
        return str;
    }

    /**
     * @Description 是否是全数字
     * @Date 2022/10/13 10:57
     * @Author pluto
     * @param str
     * @Return boolean
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 2-20个中文汉字
     * @param name
     * @return
     */
    public static boolean checkChineseName(String name) {
        String regex = "^[\u4e00-\u9fa5]{2,20}$";
        return Pattern.matches(regex, name);
    }
}
