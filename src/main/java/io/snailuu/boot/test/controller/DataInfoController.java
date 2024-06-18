package io.snailuu.boot.test.controller;

import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.test.service.DataInfoService;
import io.snailuu.boot.test.vo.DataInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@Tag(name = "首页数据查询")
public class DataInfoController {

    @Autowired
    private DataInfoService dataInfoService;

    @RequestMapping("/getDataInfo")
    public ApiResult<DataInfoVo> getDataInfo() {
        return ApiResult.success(dataInfoService.getDataInfo());
    }
}
