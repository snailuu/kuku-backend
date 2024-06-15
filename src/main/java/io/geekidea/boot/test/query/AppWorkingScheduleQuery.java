package io.geekidea.boot.test.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * App人员排班查询参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "App人员排班查询参数")
public class AppWorkingScheduleQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

}

