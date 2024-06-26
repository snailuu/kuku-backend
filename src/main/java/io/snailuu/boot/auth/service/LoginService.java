package io.snailuu.boot.auth.service;

import io.snailuu.boot.auth.dto.LoginDto;
import io.snailuu.boot.auth.vo.LoginTokenVo;
import io.snailuu.boot.auth.vo.LoginVo;
import io.snailuu.boot.system.entity.SysUser;

import java.util.Date;

/**
 * @author snailuu
 * @date 2022/7/5
 **/
public interface LoginService {

    /**
     * 登录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    LoginTokenVo login(LoginDto dto);

    /**
     * 处理登录用户信息
     *
     * @param sysUser
     * @param token
     * @param loginTime
     * @return
     * @throws Exception
     */
    LoginVo refreshLoginInfo(SysUser sysUser, String token, Date loginTime);

    /**
     * 获取登录用户信息
     *
     * @return
     * @throws Exception
     */
    LoginVo getLoginUserInfo();

    /**
     * 登出
     *
     * @throws Exception
     */
    void logout();

}
