package io.snailuu.boot.system.controller;

import io.snailuu.boot.auth.annotation.Permission;
import io.snailuu.boot.framework.bean.FastServerInfo;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.util.ServerInfoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "服务信息(用于首页数据)")
@RequestMapping("/admin/fast")
public class ServerInfoFastController {

    @PostMapping("/getServerInfoAllTime")
    @Operation(summary = "服务信息详情(cpu,jvm,mem,disk)")
    @Permission("server:info")
    public ApiResult<FastServerInfo> getServerInfo() {
        // 获取服务信息
        FastServerInfo serverInfo = ServerInfoUtil.getServerInfoFast();
        log.info("serverInfoFast:" + serverInfo);
        return ApiResult.success(serverInfo);
    }

//    @PostMapping("/getServerInfoFirst")
//    @Operation(summary = "服务信息详情(ticket,user)")
//    @Permission("server:info")


}
