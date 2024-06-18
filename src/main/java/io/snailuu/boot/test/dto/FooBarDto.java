package io.snailuu.boot.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * FooBar参数
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Data
@Schema(description = "FooBar参数")
public class FooBarDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 20, message = "名称长度超过限制")
    private String name;

    @Schema(description = "Foo")
    private String foo;

    @Schema(description = "Bar")
    private String bar;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

}


