package io.geekidea.boot.test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * App工单类型表查询结果
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Data
@Schema(description = "App工单类型表查询结果")
public class AppTypeTicketVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "工单类型id 主键id")
    private Integer id;

    @Schema(description = "类型名称")
    private String name;

}
