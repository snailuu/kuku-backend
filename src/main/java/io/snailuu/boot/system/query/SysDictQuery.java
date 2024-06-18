package io.snailuu.boot.system.query;

import io.snailuu.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 字典数据查询参数
 *
 * @author snailuu
 * @since 2023-11-25
 */
@Data
@Schema(description = "字典数据查询参数")
public class SysDictQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典类型code")
    @NotBlank(message = "字典类型不能为空")
    private String dictCode;

    @Schema(description = "状态 1：启用，0：禁用")
    private Boolean status;

}

