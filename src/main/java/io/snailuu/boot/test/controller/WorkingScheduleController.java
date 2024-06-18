package io.snailuu.boot.test.controller;

import io.snailuu.boot.auth.annotation.Permission;
import io.snailuu.boot.common.enums.SysLogType;
import io.snailuu.boot.framework.annotation.Log;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.test.dto.WorkingScheduleDto;
import io.snailuu.boot.test.query.WorkingScheduleQuery;
import io.snailuu.boot.test.service.WorkingScheduleService;
import io.snailuu.boot.test.vo.WorkingScheduleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 人员排班 控制器
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "人员排班")
@RequestMapping("/admin/workingSchedule")
public class WorkingScheduleController {

    @Autowired
    private WorkingScheduleService workingScheduleService;

    /**
     * 添加人员排班
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.ADD)
    @Operation(summary = "添加人员排班")
    @PostMapping("/addWorkingSchedule")
    @Permission("working:schedule:add")
    public ApiResult addWorkingSchedule(@Valid @RequestBody WorkingScheduleDto dto) {
        log.info("添加人员排班：{}", dto);
        boolean flag = workingScheduleService.addWorkingSchedule(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改人员排班
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.UPDATE)
    @Operation(summary = "修改人员排班")
    @PostMapping("/updateWorkingSchedule")
    @Permission("working:schedule:update")
    public ApiResult updateWorkingSchedule(@Valid @RequestBody WorkingScheduleDto dto) {
        log.info("修改人员排班：{}", dto);
        boolean flag = workingScheduleService.updateWorkingSchedule(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除人员排班
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.DELETE)
    @Operation(summary = "删除人员排班")
    @PostMapping("/deleteWorkingSchedule/{id}")
    @Permission("working:schedule:delete")
    public ApiResult deleteWorkingSchedule(@PathVariable Long id) {
        log.info("删除人员排班：{}", id);
        boolean flag = workingScheduleService.deleteWorkingSchedule(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取人员排班详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取人员排班详情")
    @PostMapping("/getWorkingSchedule/{id}")
    @Permission("working:schedule:info")
    public ApiResult<WorkingScheduleVo> getWorkingSchedule(@PathVariable Long id) {
        log.info("获取人员排班详情：{}", id);
        WorkingScheduleVo workingScheduleVo = workingScheduleService.getWorkingScheduleById(id);
        return ApiResult.success(workingScheduleVo);
    }

    /**
     * 获取人员排班分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取人员排班分页列表")
    @PostMapping("/getWorkingSchedulePage")
    @Permission("working:schedule:page")
    public ApiResult<WorkingScheduleVo> getWorkingSchedulePage(@Valid @RequestBody WorkingScheduleQuery query) {
        log.info("获取人员排班分页列表：{}", query);
        Paging<WorkingScheduleVo> paging = workingScheduleService.getWorkingSchedulePage(query);
        return ApiResult.success(paging);
    }

}
