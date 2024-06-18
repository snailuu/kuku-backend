package io.snailuu.boot.auth.service.impl;

import io.snailuu.boot.auth.dto.LoginDto;
import io.snailuu.boot.auth.service.LoginRedisService;
import io.snailuu.boot.auth.service.LoginService;
import io.snailuu.boot.auth.util.LoginUtil;
import io.snailuu.boot.auth.util.TokenUtil;
import io.snailuu.boot.auth.vo.LoginTokenVo;
import io.snailuu.boot.auth.vo.LoginVo;
import io.snailuu.boot.common.enums.SystemType;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.framework.exception.LoginException;
import io.snailuu.boot.system.entity.SysRole;
import io.snailuu.boot.system.entity.SysUser;
import io.snailuu.boot.system.mapper.SysMenuMapper;
import io.snailuu.boot.system.mapper.SysRoleMapper;
import io.snailuu.boot.system.mapper.SysUserMapper;
import io.snailuu.boot.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author snailuu
 * @date 2022/7/12
 **/
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private LoginRedisService loginRedisService;


    @Override
    public LoginTokenVo login(LoginDto dto) {
        String username = dto.getUsername();
        SysUser sysUser = sysUserMapper.getSysUserByUsername(username);
        if (sysUser == null) {
            throw new BusinessException("用户信息不存在");
        }
        // 用户ID
        Long userId = sysUser.getId();
        // 校验密码
        String dbPassword = sysUser.getPassword();
        String dbSalt = sysUser.getSalt();
        String password = dto.getPassword();
        String encryptPassword = PasswordUtil.encrypt(password, dbSalt);
        if (!encryptPassword.equals(dbPassword)) {
            throw new BusinessException("账号密码错误");
        }
        // 生成token
        String token = TokenUtil.generateAdminToken(userId);
        // 刷新登录信息
        refreshLoginInfo(sysUser, token, new Date());
        // 返回token
        LoginTokenVo loginTokenVo = new LoginTokenVo();
        loginTokenVo.setToken(token);
        return loginTokenVo;
    }

    @Override
    public LoginVo refreshLoginInfo(SysUser sysUser, String token, Date loginTime) {
        // 用户ID
        Long userId = sysUser.getId();
        // 校验用户状态
        Boolean status = sysUser.getStatus();
        if (status == false) {
            throw new BusinessException("用户已禁用");
        }
        // 查询用户角色
        Long roleId = sysUser.getRoleId();
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        if (sysRole == null) {
            throw new BusinessException("该用户不存在可用的角色");
        }
        // 设置登录用户对象
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(sysUser, loginVo);
        loginVo.setUserId(userId);
        loginVo.setLoginTime(loginTime);
        loginVo.setAdmin(sysUser.getIsAdmin());
        loginVo.setRoleCode(sysRole.getCode());
        loginVo.setRoleName(sysRole.getName());
        // 系统类型 1：管理后台，2：用户端
        loginVo.setSystemType(SystemType.ADMIN.getCode());
        // 获取登录用户的权限编码
        List<String> permissions = sysMenuMapper.getPermissionCodesByUserId(userId);
        loginVo.setPermissions(permissions);
        // 保存登录信息到redis中
        loginRedisService.setLoginVo(token, loginVo);
        return loginVo;
    }

    @Override
    public LoginVo getLoginUserInfo() {
        LoginVo loginVo = LoginUtil.getLoginVo();
        if (loginVo == null) {
            throw new LoginException("请先登录");
        }
        // 根据用户ID获取用户信息
        Long userId = loginVo.getUserId();
        // 获取用户登录时间
        Date loginTime = loginVo.getLoginTime();
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            throw new BusinessException("用户信息不存在");
        }
        // 刷新登录信息
        String token = TokenUtil.getToken();
        loginVo = refreshLoginInfo(sysUser, token, loginTime);
        return loginVo;
    }

    @Override
    public void logout() {
        try {
            // 获取token
            String token = TokenUtil.getToken();
            // 删除缓存
            loginRedisService.deleteLoginVo(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
