package io.snailuu.boot.auth.service.impl;

import io.snailuu.boot.auth.dto.AppAccountLoginDto;
import io.snailuu.boot.auth.dto.AppLoginDto;
import io.snailuu.boot.auth.service.AppLoginRedisService;
import io.snailuu.boot.auth.service.AppLoginService;
import io.snailuu.boot.auth.util.AppLoginUtil;
import io.snailuu.boot.auth.util.TokenUtil;
import io.snailuu.boot.auth.vo.AppLoginVo;
import io.snailuu.boot.auth.vo.LoginTokenVo;
import io.snailuu.boot.common.constant.LoginConstant;
import io.snailuu.boot.common.enums.SystemType;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.framework.exception.LoginException;
import io.snailuu.boot.user.entity.User;
import io.snailuu.boot.user.entity.UserRole;
import io.snailuu.boot.user.service.UserRoleService;
import io.snailuu.boot.user.service.UserService;
import io.snailuu.boot.util.IpRegionUtil;
import io.snailuu.boot.util.IpUtil;
import io.snailuu.boot.util.PasswordUtil;
import io.snailuu.boot.util.api.WxMpApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author snailuu
 * @date 2022/7/12
 **/
@Slf4j
@Service
public class AppLoginServiceImpl implements AppLoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private AppLoginRedisService appLoginRedisService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public LoginTokenVo login(AppLoginDto dto) {
        String code = dto.getCode();
        // 获取微信openid
        String openid = WxMpApi.getOpenid(code);
        // 根据openid获取用户信息
        User user = userService.getUserByOpenid(openid);
        // 判断是否存在，不存在，则添加
        if (user == null) {
            // 注册
            user = new User();
            // 用户昵称：用户+8为随机数字
            user.setNickname("用户" + RandomStringUtils.randomNumeric(8));
            // 设置微信小程序openid
            user.setOpenid(openid);
            // 设置注册时间
            user.setRegisterTime(new Date());
            String requestIp = IpUtil.getRequestIp();
            String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
            // 设置注册IP
            user.setRegisterIp(requestIp);
            // 设置注册IP区域
            user.setRegisterIpArea(ipAreaDesc);
            // 设置默认用户为普通角色
            user.setUserRoleId(LoginConstant.APP_NORMAL_USER_ROLE);
            boolean flag = userService.save(user);
            if (!flag) {
                throw new BusinessException("注册异常");
            }
            // 获取用户ID
            Long userId = user.getId();
            // 根据用户ID获取用户下信息
            user = userService.getById(userId);
        }
        return login(user);
    }

    @Override
    public LoginTokenVo accountLogin(AppAccountLoginDto dto) {
        String username = dto.getUsername();
        User user = userService.getUserByUsername(username);
        // 校验密码
        String dbPassword = user.getPassword();
        String dbSalt = user.getSalt();
        String password = dto.getPassword();
        String encryptPassword = PasswordUtil.encrypt(password, dbSalt);
        if (!encryptPassword.equals(dbPassword)) {
            throw new BusinessException("账号密码错误");
        }
        return login(user);
    }

    @Override
    public LoginTokenVo login(User user) {
        if (user == null) {
            throw new LoginException("用户信息不存在");
        }
        Long userId = user.getId();
        // 生成token
        String token = TokenUtil.generateAppToken(userId);
        // 最后一次登录时间
        Date lastLoginTime = new Date();
        // 刷新登录信息
        refreshLoginInfo(user, token, lastLoginTime);
        // 设置登录信息
        String requestIp = IpUtil.getRequestIp();
        String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
        // 设置最后登录时间
        user.setLastLoginTime(lastLoginTime);
        // 设置最后登录IP
        user.setLastLoginIp(requestIp);
        // 设置最后登录IP区域
        user.setLastLoginIpArea(ipAreaDesc);
        userService.updateById(user);

        // TODO 添加用户登录日志

        // 返回token
        LoginTokenVo loginTokenVo = new LoginTokenVo();
        loginTokenVo.setToken(token);
        return loginTokenVo;
    }

    @Override
    public AppLoginVo refreshLoginInfo(User user, String token, Date lastLoginTime) {
        Long userId = user.getId();
        // 校验用户状态
        Boolean status = user.getStatus();
        if (status == false) {
            throw new LoginException("用户已禁用");
        }
        // 设置登录用户对象
        AppLoginVo appLoginVo = new AppLoginVo();
        BeanUtils.copyProperties(user, appLoginVo);
        appLoginVo.setUserId(userId);
        appLoginVo.setLastLoginTime(lastLoginTime);
        // 系统类型 1：管理后台，2：用户端
        appLoginVo.setSystemType(SystemType.APP.getCode());
        Long userRoleId = user.getUserRoleId();
        appLoginVo.setUserRoleId(userRoleId);
        if (userRoleId != null) {
            UserRole userRole = userRoleService.getById(userRoleId);
            if (userRole != null) {
                appLoginVo.setUserRoleCode(userRole.getCode());
                appLoginVo.setUserRoleName(userRole.getName());
            }

        }
        // 保存登录信息到redis中
        appLoginRedisService.setLoginVo(token, appLoginVo);
        return appLoginVo;
    }

    @Override
    public AppLoginVo getLoginUserInfo() {
        AppLoginVo appLoginVo = AppLoginUtil.getLoginVo();
        if (appLoginVo == null) {
            throw new LoginException("请先登录");
        }
        Long userId = appLoginVo.getUserId();
        User user = userService.getById(userId);
        // 刷新用户登录信息
        String token = TokenUtil.getToken();
        Date lastLoginTime = appLoginVo.getLastLoginTime();
        appLoginVo = refreshLoginInfo(user, token, lastLoginTime);
        return appLoginVo;
    }

    @Override
    public void logout() {
        // 获取token
        String token = TokenUtil.getToken();
        // 删除缓存
        appLoginRedisService.deleteLoginVo(token);
    }
}
