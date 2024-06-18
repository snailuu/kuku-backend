package io.snailuu.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 修改系统用户密码
 *
 * @author snailuu
 * @date 2023/6/24
 **/
@Data
@Schema(description = "修改系统用户密码")
public class SysUserUpdatePasswordDto {

    @Schema(description = "旧密码")
    @NotNull(message = "旧密码不能为空")
    @Length(min = 32, max = 32, message = "旧密码长度不符合要求")
    private String oldPassword;

    @Schema(description = "新密码")
    @NotNull(message = "新密码不能为空")
    @Length(min = 32, max = 32, message = "新密码长度不符合要求")
    private String password;

    @Schema(description = "确认新密码")
    @NotNull(message = "确认新密码不能为空")
    @Length(min = 32, max = 32, message = "确认新密码长度不符合要求")
    private String confirmPassword;

}
