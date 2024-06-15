package io.geekidea.boot.test.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 工单查询参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "工单查询参数")
public class TicketQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "工单标题")
    private String title;

    @Schema(description = "状态")
    private Integer status;

}

