package io.geekidea.boot.test.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * App工单类型表查询参数
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Data
@Schema(description = "App工单类型表查询参数")
public class AppTypeTicketQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

}

