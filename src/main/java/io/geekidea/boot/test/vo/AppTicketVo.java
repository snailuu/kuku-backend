package io.geekidea.boot.test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * App工单查询结果
 *
 * @author geekidea
 * @since 2024-06-15
 */
@Data
@Schema(description = "App工单查询结果")
public class AppTicketVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "工单id 主键id")
    private Long id;

    @Schema(description = "工单uuid 提供给前端显示用")
    private String uuid;

    @Schema(description = "用户id 工单创建者id")
    private Long userId;

    @Schema(description = "工单标题")
    private String title;

    @Schema(description = "内容 工单详细内容")
    private String body;

    @Schema(description = "图片")
    private String pictures;

    @Schema(description = "状态 0:未提交，草稿;1:未审核；2:审核通过；3:退回、审核未通过")
    private Integer status;

    @Schema(description = "工单类型")
    private Integer ticketType;

    @Schema(description = "联系邮箱")
    private String contactEmail;

    @Schema(description = "联系手机号")
    private String contactPhone;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "创建时间")
    private Date createdTime;

    @Schema(description = "更新人")
    private String updatedBy;

    @Schema(description = "更新时间")
    private Date updatedTime;

}
