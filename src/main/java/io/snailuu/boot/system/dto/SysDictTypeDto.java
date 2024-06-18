package io.snailuu.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 修改字典类型参数
 *
 * @author snailuu
 * @since 2023-11-25
 */
@Data
@Schema(description = "字典类型参数")
public class SysDictTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "字典类型编码")
    @NotBlank(message = "字典类型编码不能为空")
    @Length(max = 100, message = "字典类型编码长度超过限制")
    private String code;

    @Schema(description = "字典类型名称")
    @NotBlank(message = "字典类型名称不能为空")
    @Length(max = 100, message = "字典类型名称长度超过限制")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否是系统字典类型")
    private Boolean isSystem;

}


