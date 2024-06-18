package io.snailuu.boot.auth.util;

import io.snailuu.boot.auth.cache.LoginCache;
import io.snailuu.boot.auth.service.LoginRedisService;
import io.snailuu.boot.auth.vo.LoginVo;
import io.snailuu.boot.framework.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author snailuu
 * @date 2022/6/26
 **/
@Component
public class LoginUtil {

    private static LoginRedisService loginRedisService;

    public LoginUtil(LoginRedisService loginRedisService) {
        LoginUtil.loginRedisService = loginRedisService;
    }

    /**
     * 根据token从redis中获取登录用户信息
     *
     * @param token
     * @return
     * @
     */
    public static LoginVo getLoginVo(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        LoginVo loginVo = loginRedisService.getLoginVo(token);
        return loginVo;
    }

    /**
     * 从当前线程中获取登录用户信息
     *
     * @return
     */
    public static LoginVo getLoginVo() {
        return LoginCache.get();
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public static List<String> getPermissions() {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.getPermissions();
        }
        return null;
    }

    /**
     * 获取登录用户ID
     *
     * @return
     */
    public static Long getUserId() {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            Long userId = loginVo.getUserId();
            return userId;
        }
        return null;
    }

    /**
     * 获取登录用户ID字符串
     *
     * @return
     * @
     */
    public static String getUserIdString() {
        Long userId = getUserId();
        if (userId == null) {
            return null;
        }
        return userId.toString();
    }

    /**
     * 获取登录用户名
     *
     * @return
     */
    public static String getUsername() {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.getUsername();
        }
        return null;
    }

    /**
     * 获取登录角色ID
     *
     * @return
     */
    public static Long getRoleId() {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.getRoleId();
        }
        return null;
    }

    /**
     * 判断是否是管理员
     *
     * @return
     * @
     */
    public static boolean isAdmin() {
        LoginVo loginVo = getLoginVo();
        if (loginVo != null) {
            return loginVo.isAdmin();
        }
        return false;
    }

    /**
     * 判断不是管理员
     *
     * @return
     * @
     */
    public static boolean isNotAdmin() {
        return !isAdmin();
    }

    /**
     * 检查是否是管理员
     */
    public static void checkAdmin() {
        boolean admin = isAdmin();
        if (!admin) {
            throw new BusinessException("不是管理员，无权限");
        }
    }

}
