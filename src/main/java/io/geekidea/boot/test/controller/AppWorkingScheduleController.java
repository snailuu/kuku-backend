package io.geekidea.boot.test.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.query.AppWorkingScheduleQuery;
import io.geekidea.boot.test.service.WorkingScheduleService;
import io.geekidea.boot.test.vo.AppWorkingScheduleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App人员排班表 控制器
 *
 * @author geekidea
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "App人员排班表")
@RequestMapping("/app/workingSchedule")
public class AppWorkingScheduleController {

    @Autowired
    private WorkingScheduleService workingScheduleService;

    /**
     * 获取App人员排班表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App人员排班表详情")
    @PostMapping("/getAppWorkingSchedule/{id}")
    public ApiResult<AppWorkingScheduleVo> getAppWorkingSchedule(@PathVariable Long id) {
        log.info("获取App人员排班表详情：{}", id);
        AppWorkingScheduleVo appWorkingScheduleVo = workingScheduleService.getAppWorkingScheduleById(id);
        return ApiResult.success(appWorkingScheduleVo);
    }

    /**
     * 获取App人员排班表分页列表
     *
     * query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App人员排班表分页列表")
    @PostMapping("/getAppWorkingSchedulePage")
    public ApiResult<AppWorkingScheduleVo> getAppWorkingSchedulePage(@Valid @RequestBody AppWorkingScheduleQuery query) {
        log.info("获取App人员排班表分页列表：{}", query);
        Paging<AppWorkingScheduleVo> paging = workingScheduleService.getAppWorkingSchedulePage(query);
        return ApiResult.success(paging);
    }

}
