package io.snailuu.boot.auth.service;

import io.snailuu.boot.auth.dto.RegisterDto;

public interface RegisterService {

    // 注册
    boolean register(RegisterDto dto);
}
