package io.snailuu.boot.auth.service;

import io.snailuu.boot.auth.dto.AppAccountLoginDto;
import io.snailuu.boot.auth.dto.AppLoginDto;
import io.snailuu.boot.auth.vo.AppLoginVo;
import io.snailuu.boot.auth.vo.LoginTokenVo;
import io.snailuu.boot.user.entity.User;

import java.util.Date;

/**
 * @author snailuu
 * @date 2022/7/5
 **/
public interface AppLoginService {

    /**
     * APP小程序登录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    LoginTokenVo login(AppLoginDto dto);

    /**
     * APP账号密码登录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    LoginTokenVo accountLogin(AppAccountLoginDto dto);

    /**
     * APP登录
     *
     * @param user
     * @return
     * @throws Exception
     */
    LoginTokenVo login(User user);

    /**
     * 刷新登录信息
     *
     * @param user
     * @param token
     * @param lastLoginTime
     * @return
     * @throws Exception
     */
    AppLoginVo refreshLoginInfo(User user, String token, Date lastLoginTime);

    /**
     * 获取登录用户信息
     *
     * @return
     * @throws Exception
     */
    AppLoginVo getLoginUserInfo();

    /**
     * 登出
     *
     * @throws Exception
     */
    void logout();

}
