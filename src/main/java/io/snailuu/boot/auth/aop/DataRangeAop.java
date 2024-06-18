package io.snailuu.boot.auth.aop;

import io.snailuu.boot.common.constant.AopConstant;
import io.snailuu.boot.framework.query.DataRangeQuery;
import io.snailuu.boot.util.DataRangeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author snailuu
 * @date 2022/4/20
 **/
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "login.admin.enable", havingValue = "true", matchIfMissing = true)
public class DataRangeAop {

    @Around(AopConstant.ADMIN_POINTCUT)
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return joinPoint.proceed();
        }
        for (Object arg : args) {
            if (arg instanceof DataRangeQuery) {
                DataRangeQuery dataRangeQuery = (DataRangeQuery) arg;
                DataRangeUtil.handleAdminLogin(dataRangeQuery);
                break;
            }
        }
        return joinPoint.proceed();
    }


}