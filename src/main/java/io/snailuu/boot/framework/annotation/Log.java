package io.snailuu.boot.framework.annotation;

import io.snailuu.boot.common.enums.SysLogType;

/**
 * @author snailuu
 * @date 2022/8/3
 **/
public @interface Log {

    /**
     * 描述
     *
     * @return
     */
    String value() default "";


    SysLogType type() default SysLogType.OTHER;

}
