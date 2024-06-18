package io.snailuu.boot.generator.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author snailuu
 * @date 2024/1/3
 **/
@Data
@Schema(description = "生成代码optionVo")
public class GeneratorOptionVo {

    private String label;

    private String value;

}
