package io.snailuu.boot.common.service.impl;

import io.snailuu.boot.common.service.AsyncService;

public class AsyncServiceImpl implements AsyncService {
    @Override
    public void sendSMS() throws Exception {
        System.out.println("调用信息方法开始...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(5000);
        Long endTime = System.currentTimeMillis();
        System.out.println("业务执行完成，耗时："+(endTime-startTime));

    }
}
