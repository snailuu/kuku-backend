package io.snailuu.boot.test.controller;

import io.snailuu.boot.auth.annotation.Permission;
import io.snailuu.boot.common.enums.SysLogType;
import io.snailuu.boot.framework.annotation.Log;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.test.dto.TicketDto;
import io.snailuu.boot.test.query.TicketQuery;
import io.snailuu.boot.test.service.TicketService;
import io.snailuu.boot.test.vo.TicketVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 工单 控制器
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "工单")
@RequestMapping("/admin/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * 添加工单
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.ADD)
    @Operation(summary = "添加工单")
    @PostMapping("/addTicket")
    @Permission("ticket:add")
    public ApiResult addTicket(@Valid @RequestBody TicketDto dto) {
        log.info("添加工单：{}", dto);
        boolean flag = ticketService.addTicket(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改工单
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.UPDATE)
    @Operation(summary = "修改工单")
    @PostMapping("/updateTicket")
    @Permission("ticket:update")
    public ApiResult updateTicket(@Valid @RequestBody TicketDto dto) {
        log.info("修改工单：{}", dto);
        boolean flag = ticketService.updateTicket(dto);
        return ApiResult.result(flag);
    }

    @Log(type = SysLogType.UPDATE)
    @Operation(summary = "审核工单")
    @PostMapping("/updateTicketStatus")
    @Permission("ticket:update")
    public ApiResult updateTicketStatus(@Valid @RequestBody Long id) {
        boolean flag = ticketService.updateTicketStatus(id);
        return ApiResult.result(flag);
    }

    /**
     * 删除工单
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(type = SysLogType.DELETE)
    @Operation(summary = "删除工单")
    @PostMapping("/deleteTicket/{id}")
    @Permission("ticket:delete")
    public ApiResult deleteTicket(@PathVariable Long id) {
        log.info("删除工单：{}", id);
        boolean flag = ticketService.deleteTicket(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取工单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取工单详情")
    @PostMapping("/getTicket/{id}")
    @Permission("ticket:info")
    public ApiResult<TicketVo> getTicket(@PathVariable Long id) {
        log.info("获取工单详情：{}", id);
        TicketVo ticketVo = ticketService.getTicketById(id);
        return ApiResult.success(ticketVo);
    }

    /**
     * 获取工单分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取工单分页列表")
    @PostMapping("/getTicketPage")
    @Permission("ticket:page")
    public ApiResult<TicketVo> getTicketPage(@Valid @RequestBody TicketQuery query) {
        log.info("获取工单分页列表：{}", query);
        Paging<TicketVo> paging = ticketService.getTicketPage(query);
        return ApiResult.success(paging);
    }

}
