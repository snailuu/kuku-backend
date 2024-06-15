package io.geekidea.boot.test.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.query.AppFooBarQuery;
import io.geekidea.boot.test.service.FooBarService;
import io.geekidea.boot.test.vo.AppFooBarVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * AppFooBar 控制器
 *
 * @author geekidea
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "AppFooBar")
@RequestMapping("/app/fooBar")
public class AppFooBarController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 获取AppFooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取AppFooBar详情")
    @PostMapping("/getAppFooBar/{id}")
    public ApiResult<AppFooBarVo> getAppFooBar(@PathVariable Long id) {
        log.info("获取AppFooBar详情：{}", id);
        AppFooBarVo appFooBarVo = fooBarService.getAppFooBarById(id);
        return ApiResult.success(appFooBarVo);
    }

    /**
     * 获取AppFooBar分页列表
     *
     * query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取AppFooBar分页列表")
    @PostMapping("/getAppFooBarPage")
    public ApiResult<AppFooBarVo> getAppFooBarPage(@Valid @RequestBody AppFooBarQuery query) {
        log.info("获取AppFooBar分页列表：{}", query);
        Paging<AppFooBarVo> paging = fooBarService.getAppFooBarPage(query);
        return ApiResult.success(paging);
    }

}
