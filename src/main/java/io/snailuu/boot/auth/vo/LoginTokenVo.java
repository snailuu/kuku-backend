package io.snailuu.boot.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author snailuu
 * @date 2022/6/26
 **/
@Data
@Schema(description = "LoginVo")
public class LoginTokenVo {

    @Schema(description = "登录令牌")
    private String token;

}
