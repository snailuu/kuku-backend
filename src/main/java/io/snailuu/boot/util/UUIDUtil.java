package io.snailuu.boot.util;

import java.util.UUID;

/**
 * @author snailuu
 * @date 2022/6/26
 **/
public class UUIDUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
