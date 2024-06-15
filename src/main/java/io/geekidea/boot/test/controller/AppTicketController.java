package io.geekidea.boot.test.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.query.AppTicketQuery;
import io.geekidea.boot.test.service.TicketService;
import io.geekidea.boot.test.vo.AppTicketVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App工单 控制器
 *
 * @author geekidea
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "App工单")
@RequestMapping("/app/ticket")
public class AppTicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * 获取App工单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App工单详情")
    @PostMapping("/getAppTicket/{id}")
    public ApiResult<AppTicketVo> getAppTicket(@PathVariable Long id) {
        log.info("获取App工单详情：{}", id);
        AppTicketVo appTicketVo = ticketService.getAppTicketById(id);
        return ApiResult.success(appTicketVo);
    }

    /**
     * 获取App工单分页列表
     *
     * query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App工单分页列表")
    @PostMapping("/getAppTicketPage")
    public ApiResult<AppTicketVo> getAppTicketPage(@Valid @RequestBody AppTicketQuery query) {
        log.info("获取App工单分页列表：{}", query);
        Paging<AppTicketVo> paging = ticketService.getAppTicketPage(query);
        return ApiResult.success(paging);
    }

}
