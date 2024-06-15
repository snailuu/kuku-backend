package io.geekidea.boot.test.controller;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.test.query.AppArrangeQuery;
import io.geekidea.boot.test.service.ArrangeService;
import io.geekidea.boot.test.vo.AppArrangeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * App值班安排 控制器
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@RestController
@Tag(name = "App值班安排")
@RequestMapping("/app/arrange")
public class AppArrangeController {

    @Autowired
    private ArrangeService arrangeService;

    /**
     * 获取App值班安排详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App值班安排详情")
    @PostMapping("/getAppArrange/{id}")
    public ApiResult<AppArrangeVo> getAppArrange(@PathVariable Long id) {
        log.info("获取App值班安排详情：{}", id);
        AppArrangeVo appArrangeVo = arrangeService.getAppArrangeById(id);
        return ApiResult.success(appArrangeVo);
    }

    /**
     * 获取App值班安排分页列表
     *
     * query
     * @return
     * @throws Exception
     */
    @Operation(summary = "获取App值班安排分页列表")
    @PostMapping("/getAppArrangePage")
    public ApiResult<AppArrangeVo> getAppArrangePage(@Valid @RequestBody AppArrangeQuery query) {
        log.info("获取App值班安排分页列表：{}", query);
        Paging<AppArrangeVo> paging = arrangeService.getAppArrangePage(query);
        return ApiResult.success(paging);
    }

}
