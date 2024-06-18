package io.snailuu.boot.auth.annotation;

import java.lang.annotation.*;

/**
 * 检查App端用户角色
 *
 * @author snailuu
 * @date 2024/1/6
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Login
public @interface AppUserRole {

    /**
     * 角色ID或角色编码
     *
     * @return
     */
    String[] value() default {};

}
