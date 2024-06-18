package io.snailuu.boot.user.query;

import io.snailuu.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户角色查询参数
 *
 * @author snailuu
 * @since 2024-01-06
 */
@Data
@Schema(description = "用户角色查询参数")
public class UserRoleQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

}

