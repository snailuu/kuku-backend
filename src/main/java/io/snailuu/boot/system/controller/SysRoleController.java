package io.snailuu.boot.system.controller;

import io.snailuu.boot.auth.annotation.Permission;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.system.dto.RoleMenusDto;
import io.snailuu.boot.system.dto.SysRoleDto;
import io.snailuu.boot.system.entity.SysRole;
import io.snailuu.boot.system.query.SysRoleQuery;
import io.snailuu.boot.system.service.SysMenuService;
import io.snailuu.boot.system.service.SysRoleService;
import io.snailuu.boot.system.vo.SysRoleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统角色 控制器
 *
 * @author snailuu
 * @since 2022-12-26
 */
@Slf4j
@RestController
@Tag(name = "系统角色")
@RequestMapping("/admin/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 添加系统角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/addSysRole")
    @Operation(summary = "添加系统角色")
    @Permission("sys:role:add")
    public ApiResult addSysRole(@Valid @RequestBody SysRoleDto dto) {
        boolean flag = sysRoleService.addSysRole(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/updateSysRole")
    @Operation(summary = "修改系统角色")
    @Permission("sys:role:update")
    public ApiResult updateSysRole(@Valid @RequestBody SysRoleDto dto) {
        boolean flag = sysRoleService.updateSysRole(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteSysRole/{id}")
    @Operation(summary = "删除系统角色")
    @Permission("sys:role:delete")
    public ApiResult deleteSysRole(@PathVariable Long id) {
        boolean flag = sysRoleService.deleteSysRole(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRole/{id}")
    @Operation(summary = "系统角色详情")
    @Permission("sys:role:info")
    public ApiResult<SysRoleVo> getSysRole(@PathVariable Long id) {
        SysRoleVo sysRoleVo = sysRoleService.getSysRoleById(id);
        return ApiResult.success(sysRoleVo);
    }

    /**
     * 系统角色分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRolePage")
    @Operation(summary = "系统角色分页列表")
    @Permission("sys:role:page")
    public ApiResult<SysRoleVo> getSysRolePage(@Valid @RequestBody SysRoleQuery query) {
        Paging<SysRoleVo> paging = sysRoleService.getSysRolePage(query);
        return ApiResult.success(paging);
    }

    /**
     * 系统所有角色列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysRoleAllList")
    @Operation(summary = "系统所有角色列表")
    public ApiResult<SysRole> getSysRoleAllList() {
        List<SysRole> list = sysRoleService.getSysRoleAllList();
        return ApiResult.success(list);
    }

    /**
     * 设置角色权限
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/setRoleMenus")
    @Operation(summary = "设置角色权限")
    @Permission("sys:role:set-role-menus")
    public ApiResult setRoleMenus(@Valid @RequestBody RoleMenusDto roleMenusDto) {
        boolean flag = sysRoleService.setRoleMenus(roleMenusDto);
        return ApiResult.success(flag);
    }

    /**
     * 获取角色权限ID集合
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getMenuIdsByRoleId/{roleId}")
    @Operation(summary = "获取角色权限ID集合")
    public ApiResult getMenuIdsByRoleId(@PathVariable Long roleId) {
        List<Long> list = sysMenuService.getMenuIdsByRoleId(roleId);
        return ApiResult.success(list);
    }

}
