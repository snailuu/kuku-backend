package io.geekidea.boot.test.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

/**
 * 值班安排
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@TableName("arrange")
@Schema(description = "值班安排")
public class Arrange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "班次id 主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "班次类型：1早班、2午班、3晚班")
    private String type;

    @Schema(description = "早班开始时间段")
    private Time morningBegin;

    @Schema(description = "早班结束时间段")
    private Time morningEnd;

    @Schema(description = "午班开始时间段")
    private Time afternoonBegin;

    @Schema(description = "午班结束时间段")
    private Time afternoonEnd;

    @Schema(description = "晚班开始时间段")
    private Time nightBegin;

    @Schema(description = "晚班结束时间段")
    private Time nightEnd;

}

