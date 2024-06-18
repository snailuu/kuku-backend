package io.snailuu.boot.common.service;

import org.springframework.scheduling.annotation.Async;

public interface AsyncService {
    @Async
    public void sendSMS()throws Exception;

}
