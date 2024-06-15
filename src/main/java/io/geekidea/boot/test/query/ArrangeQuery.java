package io.geekidea.boot.test.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Time;

/**
 * 值班安排查询参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "值班安排查询参数")
public class ArrangeQuery extends BasePageQuery {

    private static final long serialVersionUID = 1L;

    @Schema(description = "班次类型")
    private String type;

    @Schema(description = "早班开始")
    private Time morningBegin;

    @Schema(description = "早班结束")
    private Time morningEnd;

    @Schema(description = "午班开始")
    private Time afternoonBegin;

    @Schema(description = "午班结束")
    private Time afternoonEnd;

    @Schema(description = "晚班开始")
    private Time nightBegin;

    @Schema(description = "晚班结束")
    private Time nightEnd;

}

