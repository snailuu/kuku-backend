package io.snailuu.boot.test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "首页部分数据查询结果")
public class DataInfoVo implements Serializable {
    private static final long serialVersionUID = 3629191986817747918L;

    @Schema(description = "总工单数")
    private Integer totalTicket;

    @Schema(description = "总用户数")
    private Integer totalUser;

    @Schema(description = "总待完成工单数")
    private Integer totalReadyTicket;

    @Schema(description = "最近7天完成工单数")
    private List<TicketListVo> recentTicket;

    @Schema(description = "最近7天待完成工单数")
    private List<Integer> recentReadyTicket;


}
