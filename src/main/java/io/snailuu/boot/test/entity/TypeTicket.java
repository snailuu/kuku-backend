package io.snailuu.boot.test.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 工单类型表
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Data
@TableName("type_ticket")
@Schema(description = "工单类型表")
public class TypeTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "工单类型id 主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @Schema(description = "类型名称")
    private String name;

}

