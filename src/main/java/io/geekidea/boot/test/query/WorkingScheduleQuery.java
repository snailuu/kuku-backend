package io.geekidea.boot.test.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 人员排班查询参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "人员排班查询参数")
public class WorkingScheduleQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @Schema(description = "值班安排")
    private String arrangeType;

    @Schema(description = "值班人员")
    private Long userId;

    @Schema(description = "开始的值班日期")
    private Date workingDateStart;

    @Schema(description = "结束的值班日期")
    private Date workingDateEnd;

    @Schema(description = "值班状态")
    private String status;

}

