package io.snailuu.boot.test.query;

import io.snailuu.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * 工单类型表查询参数
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Data
@Schema(description = "工单类型表查询参数")
public class TypeTicketQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

}

