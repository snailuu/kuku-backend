package io.snailuu.boot.auth.annotation;

import java.lang.annotation.*;

/**
 * 忽略登录的注解
 *
 * @author snailuu
 * @date 2022/6/26
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLogin {

}
