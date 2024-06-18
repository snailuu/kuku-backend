package io.snailuu.boot.auth.interceptor;

import io.snailuu.boot.auth.cache.AppLoginCache;
import io.snailuu.boot.auth.cache.LoginCache;
import io.snailuu.boot.auth.util.AppLoginUtil;
import io.snailuu.boot.auth.util.LoginUtil;
import io.snailuu.boot.auth.util.TokenUtil;
import io.snailuu.boot.auth.vo.AppLoginVo;
import io.snailuu.boot.auth.vo.LoginVo;
import io.snailuu.boot.common.enums.SystemType;
import io.snailuu.boot.framework.exception.LoginTokenException;
import io.snailuu.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import io.snailuu.boot.util.SystemTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author snailuu
 * @date 2022/6/26
 **/
@Slf4j
public class CommonLoginInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 获取token
        String token = TokenUtil.getToken();
        SystemType systemType = null;
        if (StringUtils.isNotBlank(token)) {
            systemType = SystemTypeUtil.getSystemTypeByToken(token);
            if (SystemType.ADMIN == systemType) {
                // 获取管理后台登录用户信息
                LoginVo loginVo = LoginUtil.getLoginVo(token);
                if (loginVo != null) {
                    // 将管理后台的登录信息保存到当前线程中
                    LoginCache.set(loginVo);
                }
            } else if (SystemType.APP == systemType) {
                AppLoginVo loginVo = AppLoginUtil.getLoginVo(token);
                if (loginVo != null) {
                    // 将APP移动端的登录信息保存到当前线程中
                    AppLoginCache.set(loginVo);
                }
            }
        }
        // 如果不存在@Login注解，则跳过
        boolean existsLoginAnnotation = existsLoginAnnotation(handlerMethod);
        if (!existsLoginAnnotation) {
            return true;
        }
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("请登录后再操作");
        }
        if (SystemType.ADMIN == systemType) {
            // 获取管理后台登录用户信息
            LoginVo loginVo = LoginCache.get();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        } else if (SystemType.APP == systemType) {
            AppLoginVo loginVo = AppLoginCache.get();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginCache.remove();
        AppLoginCache.remove();
    }
}
