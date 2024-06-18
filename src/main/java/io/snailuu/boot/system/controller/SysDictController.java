package io.snailuu.boot.system.controller;

import io.snailuu.boot.auth.annotation.Permission;
import io.snailuu.boot.common.enums.SysLogType;
import io.snailuu.boot.framework.annotation.Log;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.system.dto.SysDictDto;
import io.snailuu.boot.system.query.SysDictQuery;
import io.snailuu.boot.system.service.SysDictService;
import io.snailuu.boot.system.vo.SysDictVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 字典数据 控制器
 *
 * @author snailuu
 * @since 2023-11-25
 */
@Slf4j
@RestController
@Tag(name = "字典数据")
@RequestMapping("/admin/sysDict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 添加字典数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加字典数据", type = SysLogType.ADD)
    @PostMapping("/addSysDict")
    @Operation(summary = "添加字典数据")
    @Permission("sys:dict:add")
    public ApiResult addSysDict(@Valid @RequestBody SysDictDto dto) {
        boolean flag = sysDictService.addSysDict(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改字典数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改字典数据", type = SysLogType.UPDATE)
    @PostMapping("/updateSysDict")
    @Operation(summary = "修改字典数据")
    @Permission("sys:dict:update")
    public ApiResult updateSysDict(@Valid @RequestBody SysDictDto dto) {
        boolean flag = sysDictService.updateSysDict(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除字典数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除字典数据", type = SysLogType.DELETE)
    @PostMapping("/deleteSysDict/{id}")
    @Operation(summary = "删除字典数据")
    @Permission("sys:dict:delete")
    public ApiResult deleteSysDict(@PathVariable Long id) {
        boolean flag = sysDictService.deleteSysDict(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取字典数据详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDict/{id}")
    @Operation(summary = "字典数据详情")
    @Permission("sys:dict:info")
    public ApiResult<SysDictVo> getSysDict(@PathVariable Long id) {
        SysDictVo sysDictVo = sysDictService.getSysDictById(id);
        return ApiResult.success(sysDictVo);
    }

    /**
     * 字典数据分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDictPage")
    @Operation(summary = "字典数据分页列表")
    @Permission("sys:dict:page")
    public ApiResult<SysDictVo> getSysDictPage(@Valid @RequestBody SysDictQuery query) {
        Paging<SysDictVo> paging = sysDictService.getSysDictPage(query);
        return ApiResult.success(paging);
    }

}
