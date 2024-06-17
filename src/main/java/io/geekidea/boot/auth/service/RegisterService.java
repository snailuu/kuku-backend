package io.geekidea.boot.auth.service;

import io.geekidea.boot.auth.dto.RegisterDto;
import io.geekidea.boot.auth.vo.RegisterVo;

public interface RegisterService {

    // 注册
    boolean register(RegisterDto dto);
}
