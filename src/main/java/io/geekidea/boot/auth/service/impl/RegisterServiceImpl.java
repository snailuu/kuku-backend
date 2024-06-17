package io.geekidea.boot.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.auth.dto.RegisterDto;
import io.geekidea.boot.auth.service.RegisterService;
import io.geekidea.boot.auth.vo.RegisterVo;
import io.geekidea.boot.system.entity.SysUser;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.service.SysUserService;
import io.geekidea.boot.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RegisterServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements RegisterService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(RegisterDto dto) {
        sysUserService.checkUsernameExists(dto.getUsername());
        SysUser sysUser = new SysUser();
        sysUser.setNickname(dto.getUsername());
        sysUser.setStatus(true);
        sysUser.setRoleId(2L);
        sysUser.setIsAdmin(false);

        BeanUtils.copyProperties(dto, sysUser);
        // 密码加盐
        String salt = RandomStringUtils.randomNumeric(6);
        sysUser.setSalt(salt);
        String password = PasswordUtil.encrypt(sysUser.getPassword(), salt);
        sysUser.setPassword(password);
        // 保存用户
        return save(sysUser);
    }
}
