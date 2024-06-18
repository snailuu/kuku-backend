package io.snailuu.boot.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.snailuu.boot.user.entity.UserRole;
import io.snailuu.boot.user.query.UserRoleQuery;
import io.snailuu.boot.user.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色 Mapper接口
 *
 * @author snailuu
 * @since 2024-01-06
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 用户角色详情
     *
     * @param id
     * @return
     */
    UserRoleVo getUserRoleById(Long id);

    /**
     * 用户角色分页列表
     *
     * @param query
     * @return
     */
    List<UserRoleVo> getUserRolePage(UserRoleQuery query);

}
