package io.snailuu.boot.framework.exception;

/**
 * 登录异常
 *
 * @author snailuu
 * @date 2018-11-08
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }

}
