package io.snailuu.boot.framework.exception;

/**
 * 业务异常
 *
 * @author snailuu
 * @date 2018-11-08
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
