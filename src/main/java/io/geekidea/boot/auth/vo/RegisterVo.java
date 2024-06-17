package io.geekidea.boot.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "RegisterVo")
public class RegisterVo {
    @Schema(description = "账号")
    private String username;
}
