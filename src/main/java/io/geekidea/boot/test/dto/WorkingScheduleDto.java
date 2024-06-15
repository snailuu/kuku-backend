package io.geekidea.boot.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 人员排班表参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "人员排班表参数")
public class WorkingScheduleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "排班id 主键id")
    private Long id;

    @Schema(description = "值班安排 外键arrange->id")
    @NotNull(message = "值班安排不能为空")
    private Long arrangeId;

    @Schema(description = "值班人员 外键user->id")
    @NotNull(message = "值班人员不能为空")
    private Long userId;

    @Schema(description = "值班日期")
    @NotNull(message = "值班日期不能为空")
    private Date workingDate;

    @Schema(description = "值班状态：1计划中、2进行中、3已打卡、4缺勤")
    private String status;

}


