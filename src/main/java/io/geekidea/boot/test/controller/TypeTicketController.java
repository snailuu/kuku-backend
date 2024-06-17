package io.geekidea.boot.test.controller;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.dto.TypeTicketDto;
import io.geekidea.boot.test.query.TypeTicketQuery;
import io.geekidea.boot.test.service.TypeTicketService;
import io.geekidea.boot.test.vo.TypeTicketVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 工单类型表 控制器
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Slf4j
@RestController
@Tag(name = "工单类型表")
@RequestMapping("/admin/typeTicket")
public class TypeTicketController {

    @Autowired
    private TypeTicketService typeTicketService;

    /**
     * 添加工单类型表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.ADD)
    @Operation(summary = "添加工单类型表")
    @PostMapping("/addTypeTicket")
    @Permission("type:ticket:add")
    public ApiResult addTypeTicket(@Valid @RequestBody TypeTicketDto dto) {
        log.info("添加工单类型表：{}", dto);
        boolean flag = typeTicketService.addTypeTicket(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改工单类型表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.UPDATE)
    @Operation(summary = "修改工单类型表")
    @PostMapping("/updateTypeTicket")
    @Permission("type:ticket:update")
    public ApiResult updateTypeTicket(@Valid @RequestBody TypeTicketDto dto) {
        log.info("修改工单类型表：{}", dto);
        boolean flag = typeTicketService.updateTypeTicket(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除工单类型表
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.DELETE)
    @Operation(summary = "删除工单类型表")
    @PostMapping("/deleteTypeTicket/{id}")
    @Permission("type:ticket:delete")
    public ApiResult deleteTypeTicket(@PathVariable Integer id) {
        log.info("删除工单类型表：{}", id);
        boolean flag = typeTicketService.deleteTypeTicket(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取工单类型表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取工单类型表详情")
    @PostMapping("/getTypeTicket/{id}")
    @Permission("type:ticket:info")
    public ApiResult<TypeTicketVo> getTypeTicket(@PathVariable Integer id) {
        log.info("获取工单类型表详情：{}", id);
        TypeTicketVo typeTicketVo = typeTicketService.getTypeTicketById(id);
        return ApiResult.success(typeTicketVo);
    }

    /**
     * 获取工单类型表分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取工单类型表分页列表")
    @PostMapping("/getTypeTicketPage")
    @Permission("type:ticket:page")
    public ApiResult<TypeTicketVo> getTypeTicketPage(@Valid @RequestBody TypeTicketQuery query) {
        log.info("获取工单类型表分页列表：{}", query);
        Paging<TypeTicketVo> paging = typeTicketService.getTypeTicketPage(query);
        return ApiResult.success(paging);
    }

}
