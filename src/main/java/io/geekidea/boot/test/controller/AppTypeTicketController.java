package io.geekidea.boot.test.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.query.AppTypeTicketQuery;
import io.geekidea.boot.test.service.TypeTicketService;
import io.geekidea.boot.test.vo.AppTypeTicketVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App工单类型表 控制器
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Slf4j
@RestController
@Tag(name = "App工单类型表")
@RequestMapping("/app/typeTicket")
public class AppTypeTicketController {

    @Autowired
    private TypeTicketService typeTicketService;

    /**
     * 获取App工单类型表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App工单类型表详情")
    @PostMapping("/getAppTypeTicket/{id}")
    public ApiResult<AppTypeTicketVo> getAppTypeTicket(@PathVariable Integer id) {
        log.info("获取App工单类型表详情：{}", id);
        AppTypeTicketVo appTypeTicketVo = typeTicketService.getAppTypeTicketById(id);
        return ApiResult.success(appTypeTicketVo);
    }

    /**
     * 获取App工单类型表分页列表
     *
     * query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App工单类型表分页列表")
    @PostMapping("/getAppTypeTicketPage")
    public ApiResult<AppTypeTicketVo> getAppTypeTicketPage(@Valid @RequestBody AppTypeTicketQuery query) {
        log.info("获取App工单类型表分页列表：{}", query);
        Paging<AppTypeTicketVo> paging = typeTicketService.getAppTypeTicketPage(query);
        return ApiResult.success(paging);
    }

}
