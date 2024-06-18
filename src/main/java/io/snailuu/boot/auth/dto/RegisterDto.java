package io.snailuu.boot.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "RegisterDto")
public class RegisterDto {
    @Schema(description = "用户名", example = "admin")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", example = "e10adc3949ba59abbe56e057f20f883e")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "电子邮件", example = "1001011@qq.com")
    @NotBlank(message = "联系邮件不能为空")
    private String email;
}
