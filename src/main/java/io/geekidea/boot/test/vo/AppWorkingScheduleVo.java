package io.geekidea.boot.test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * App人员排班表查询结果
 *
 * @author geekidea
 * @since 2024-06-15
 */
@Data
@Schema(description = "App人员排班表查询结果")
public class AppWorkingScheduleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "排班id 主键id")
    private Long id;

    @Schema(description = "值班安排 外键arrange->id")
    private Long arrangeId;

    @Schema(description = "值班人员 外键user->id")
    private Long userId;

    @Schema(description = "值班日期")
    private Date workingDate;

    @Schema(description = "值班状态：1计划中、2进行中、3已打卡、4缺勤")
    private String status;

}
