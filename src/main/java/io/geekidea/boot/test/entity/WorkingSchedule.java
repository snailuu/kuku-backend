package io.geekidea.boot.test.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员排班
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@TableName("working_schedule")
@Schema(description = "人员排班")
public class WorkingSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "排班id 主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "值班安排 外键arrange->type")
    private String arrangeType;

    @Schema(description = "值班人员 外键user->id")
    private Long userId;

    @Schema(description = "值班日期")
    private Date workingDate;

    @Schema(description = "值班状态：1计划中、2进行中、3已打卡、4缺勤")
    private String status;

}

