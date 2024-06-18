package io.snailuu.boot.test.controller;

import io.snailuu.boot.auth.annotation.Permission;
import io.snailuu.boot.common.enums.SysLogType;
import io.snailuu.boot.framework.annotation.Log;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.test.dto.FooBarDto;
import io.snailuu.boot.test.query.FooBarQuery;
import io.snailuu.boot.test.service.FooBarService;
import io.snailuu.boot.test.vo.FooBarVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * FooBar 控制器
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "FooBar")
@RequestMapping("/admin/fooBar")
public class FooBarController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 添加FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.ADD)
    @Operation(summary = "添加FooBar")
    @PostMapping("/addFooBar")
    @Permission("foo:bar:add")
    public ApiResult addFooBar(@Valid @RequestBody FooBarDto dto) {
        log.info("添加FooBar：{}", dto);
        boolean flag = fooBarService.addFooBar(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.UPDATE)
    @Operation(summary = "修改FooBar")
    @PostMapping("/updateFooBar")
    @Permission("foo:bar:update")
    public ApiResult updateFooBar(@Valid @RequestBody FooBarDto dto) {
        log.info("修改FooBar：{}", dto);
        boolean flag = fooBarService.updateFooBar(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除FooBar
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.DELETE)
    @Operation(summary = "删除FooBar")
    @PostMapping("/deleteFooBar/{id}")
    @Permission("foo:bar:delete")
    public ApiResult deleteFooBar(@PathVariable Long id) {
        log.info("删除FooBar：{}", id);
        boolean flag = fooBarService.deleteFooBar(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取FooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取FooBar详情")
    @PostMapping("/getFooBar/{id}")
    @Permission("foo:bar:info")
    public ApiResult<FooBarVo> getFooBar(@PathVariable Long id) {
        log.info("获取FooBar详情：{}", id);
        FooBarVo fooBarVo = fooBarService.getFooBarById(id);
        return ApiResult.success(fooBarVo);
    }

    /**
     * 获取FooBar分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取FooBar分页列表")
    @PostMapping("/getFooBarPage")
    @Permission("foo:bar:page")
    public ApiResult<FooBarVo> getFooBarPage(@Valid @RequestBody FooBarQuery query) {
        log.info("获取FooBar分页列表：{}", query);
        Paging<FooBarVo> paging = fooBarService.getFooBarPage(query);
        return ApiResult.success(paging);
    }

}
