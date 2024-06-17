package io.geekidea.boot.common.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface AsyncService {
    @Async
    public void sendSMS()throws Exception;

}
