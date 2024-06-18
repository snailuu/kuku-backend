package io.snailuu.boot.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author snailuu
 * @date 2022/7/13
 **/
public class PasswordUtil {

    private static int MD5_PWD_LENGTH = 32;

    public static String encrypt(String password, String salt) {
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (StringUtils.isBlank(salt)) {
            salt = "";
        }
        // 如果密码长度不是32为，则进行md5加密
        if (password.length() != MD5_PWD_LENGTH) {
            password = DigestUtils.md5Hex(password);
        }
        // 将md5加密后的结果+盐，再进行sha256加密
        String encryptPassword = DigestUtils.sha256Hex(password + salt);
        return encryptPassword;
    }

}
