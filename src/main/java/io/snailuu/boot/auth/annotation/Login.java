package io.snailuu.boot.auth.annotation;

import java.lang.annotation.*;

/**
 * 需要登录的注解
 *
 * @author snailuu
 * @date 2023/11/22
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

}
