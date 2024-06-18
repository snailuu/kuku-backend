package io.snailuu.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.snailuu.boot.system.vo.SysRoleVo;
import io.snailuu.boot.system.entity.SysRole;
import io.snailuu.boot.system.query.SysRoleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色 Mapper 接口
 *
 * @author snailuu
 * @since 2022-12-26
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 系统角色详情
     *
     * @param id
     * @return
     */
    SysRoleVo getSysRoleById(Long id);

    /**
     * 系统角色分页列表
     *
     * @param query
     * @return
     */
    List<SysRoleVo> getSysRolePage(SysRoleQuery query);

}
