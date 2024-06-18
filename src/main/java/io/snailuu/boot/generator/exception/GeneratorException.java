package io.snailuu.boot.generator.exception;

import io.snailuu.boot.framework.exception.BusinessException;

/**
 * @author snailuu
 * @date 2024/1/15
 **/
public class GeneratorException extends BusinessException {

    public GeneratorException(String message) {
        super(message);
    }

}
