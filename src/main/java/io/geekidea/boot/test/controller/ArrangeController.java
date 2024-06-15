package io.geekidea.boot.test.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.dto.ArrangeDto;
import io.geekidea.boot.test.query.ArrangeQuery;
import io.geekidea.boot.test.service.ArrangeService;
import io.geekidea.boot.test.vo.ArrangeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 值班安排 控制器
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "值班安排")
@RequestMapping("/admin/arrange")
public class ArrangeController {

    @Autowired
    private ArrangeService arrangeService;

    /**
     * 添加值班安排
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.ADD)
    @Operation(summary = "添加值班安排")
    @PostMapping("/addArrange")
    @Permission("arrange:add")
    public ApiResult addArrange(@Valid @RequestBody ArrangeDto dto) {
        log.info("添加值班安排：{}", dto);
        boolean flag = arrangeService.addArrange(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改值班安排
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.UPDATE)
    @Operation(summary = "修改值班安排")
    @PostMapping("/updateArrange")
    @Permission("arrange:update")
    public ApiResult updateArrange(@Valid @RequestBody ArrangeDto dto) {
        log.info("修改值班安排：{}", dto);
        boolean flag = arrangeService.updateArrange(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除值班安排
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.DELETE)
    @Operation(summary = "删除值班安排")
    @PostMapping("/deleteArrange/{id}")
    @Permission("arrange:delete")
    public ApiResult deleteArrange(@PathVariable Long id) {
        log.info("删除值班安排：{}", id);
        boolean flag = arrangeService.deleteArrange(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取值班安排详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取值班安排详情")
    @PostMapping("/getArrange/{id}")
    @Permission("arrange:info")
    public ApiResult<ArrangeVo> getArrange(@PathVariable Long id) {
        log.info("获取值班安排详情：{}", id);
        ArrangeVo arrangeVo = arrangeService.getArrangeById(id);
        return ApiResult.success(arrangeVo);
    }

    /**
     * 获取值班安排分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取值班安排分页列表")
    @PostMapping("/getArrangePage")
    @Permission("arrange:page")
    public ApiResult<ArrangeVo> getArrangePage(@Valid @RequestBody ArrangeQuery query) {
        log.info("获取值班安排分页列表：{}", query);
        Paging<ArrangeVo> paging = arrangeService.getArrangePage(query);
        return ApiResult.success(paging);
    }

}
