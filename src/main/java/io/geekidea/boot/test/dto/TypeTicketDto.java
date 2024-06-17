package io.geekidea.boot.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 工单类型表参数
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Data
@Schema(description = "工单类型表参数")
public class TypeTicketDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "工单类型id 主键id")
    private Integer id;

    @Schema(description = "类型名称")
    @NotBlank(message = "类型名称不能为空")
    @Length(max = 20, message = "类型名称长度超过限制")
    private String name;

}


