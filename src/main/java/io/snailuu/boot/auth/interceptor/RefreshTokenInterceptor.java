package io.snailuu.boot.auth.interceptor;

import io.snailuu.boot.auth.service.AppLoginRedisService;
import io.snailuu.boot.auth.service.LoginRedisService;
import io.snailuu.boot.common.enums.SystemType;
import io.snailuu.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import io.snailuu.boot.util.SystemTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author snailuu
 * @date 2022/6/26
 **/
@Slf4j
public class RefreshTokenInterceptor extends BaseExcludeMethodInterceptor {

    @Autowired
    private LoginRedisService loginRedisService;

    @Autowired
    private AppLoginRedisService appLoginRedisService;

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        SystemType systemType = SystemTypeUtil.getSystemTypeByPath(request);
        if (SystemType.ADMIN == systemType) {
            loginRedisService.refreshToken();
        } else if (SystemType.APP == systemType) {
            appLoginRedisService.refreshToken();
        }
        return true;
    }

}
