package io.snailuu.boot.user.controller;

import io.snailuu.boot.auth.annotation.Permission;
import io.snailuu.boot.common.enums.SysLogType;
import io.snailuu.boot.framework.annotation.Log;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.user.dto.UserRoleDto;
import io.snailuu.boot.user.query.UserRoleQuery;
import io.snailuu.boot.user.service.UserRoleService;
import io.snailuu.boot.user.vo.UserRoleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户角色 控制器
 *
 * @author snailuu
 * @since 2024-01-06
 */
@Slf4j
@RestController
@Tag(name = "用户角色")
@RequestMapping("/admin/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 添加用户角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加用户角色", type = SysLogType.ADD)
    @Operation(summary = "添加用户角色")
    @PostMapping("/addUserRole")
    @Permission("user:role:add")
    public ApiResult addUserRole(@Valid @RequestBody UserRoleDto dto) {
        log.info("添加用户角色：{}", dto);
        boolean flag = userRoleService.addUserRole(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改用户角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改用户角色", type = SysLogType.UPDATE)
    @Operation(summary = "修改用户角色")
    @PostMapping("/updateUserRole")
    @Permission("user:role:update")
    public ApiResult updateUserRole(@Valid @RequestBody UserRoleDto dto) {
        log.info("修改用户角色：{}", dto);
        boolean flag = userRoleService.updateUserRole(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除用户角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除用户角色", type = SysLogType.DELETE)
    @Operation(summary = "删除用户角色")
    @PostMapping("/deleteUserRole/{id}")
    @Permission("user:role:delete")
    public ApiResult deleteUserRole(@PathVariable Long id) {
        log.info("删除用户角色：{}", id);
        boolean flag = userRoleService.deleteUserRole(id);
        return ApiResult.result(flag);
    }

    /**
     * 用户角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Operation(summary = "用户角色详情")
    @PostMapping("/getUserRole/{id}")
    @Permission("user:role:info")
    public ApiResult<UserRoleVo> getUserRole(@PathVariable Long id) {
        log.info("用户角色详情：{}", id);
        UserRoleVo userRoleVo = userRoleService.getUserRoleById(id);
        return ApiResult.success(userRoleVo);
    }

    /**
     * 用户角色分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Operation(summary = "用户角色分页列表")
    @PostMapping("/getUserRolePage")
    @Permission("user:role:page")
    public ApiResult<UserRoleVo> getUserRolePage(@Valid @RequestBody UserRoleQuery query) {
        log.info("用户角色分页列表：{}", query);
        Paging<UserRoleVo> paging = userRoleService.getUserRolePage(query);
        return ApiResult.success(paging);
    }

}
