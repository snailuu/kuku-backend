package io.geekidea.boot.test.controller;

import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.service.DataInfoService;
import io.geekidea.boot.test.vo.DataInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataInfoController {

    @Autowired
    private DataInfoService dataInfoService;

    @RequestMapping("/getDataInfo")
    public ApiResult<DataInfoVo> getDataInfo() {
        return ApiResult.success(dataInfoService.getDataInfo());
    }
}
