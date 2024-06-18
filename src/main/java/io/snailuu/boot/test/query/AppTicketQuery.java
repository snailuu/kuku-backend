package io.snailuu.boot.test.query;

import io.snailuu.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * App工单查询参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "App工单查询参数")
public class AppTicketQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

}

