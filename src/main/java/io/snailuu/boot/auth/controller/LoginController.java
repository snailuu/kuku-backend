package io.snailuu.boot.auth.controller;

import io.snailuu.boot.auth.dto.LoginDto;
import io.snailuu.boot.auth.dto.RegisterDto;
import io.snailuu.boot.auth.service.LoginService;
import io.snailuu.boot.auth.service.RegisterService;
import io.snailuu.boot.auth.vo.LoginTokenVo;
import io.snailuu.boot.auth.vo.LoginVo;
import io.snailuu.boot.auth.vo.RegisterVo;
import io.snailuu.boot.common.constant.LoginConstant;
import io.snailuu.boot.framework.response.ApiResult;
import io.snailuu.boot.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author snailuu
 * @date 2022/6/26
 **/
@Slf4j
@RestController
@Tag(name = "管理后台登录")
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;



    /**
     * 管理后台登录
     *
     * @param loginDto
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @Operation(summary = "管理后台登录")
    public ApiResult<LoginTokenVo> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        LoginTokenVo loginTokenVo = loginService.login(loginDto);
        // 输出token到cookie
        CookieUtil.addCookie(LoginConstant.ADMIN_COOKIE_TOKEN_NAME, loginTokenVo.getToken(), request, response);
        return ApiResult.success(loginTokenVo);
    }

    @PostMapping("/register")
    @Operation(summary = "管理后台注册")
    public ApiResult<RegisterVo> register(@Valid @RequestBody RegisterDto registerDto, HttpServletRequest request, HttpServletResponse response){
        boolean flag = registerService.register(registerDto);
        RegisterVo registerVo = new RegisterVo();
        if(flag){
            registerVo.setUsername(registerDto.getUsername());
            return ApiResult.success(registerVo);
        }else {
            return ApiResult.fail("注册用户失败");
        }
    }

    /**
     * 获取管理后台登录用户信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getLoginUserInfo")
    @Operation(summary = "获取管理后台登录用户信息")
    public ApiResult<LoginVo> getLoginUserInfo() {
        LoginVo loginVo = loginService.getLoginUserInfo();
        return ApiResult.success(loginVo);
    }

    /**
     * 管理后台退出
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    @Operation(summary = "管理后台退出")
    public ApiResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        // 删除缓存
        loginService.logout();
        // 从cookie中删除token
        CookieUtil.deleteCookie(LoginConstant.ADMIN_COOKIE_TOKEN_NAME, request, response);
        return ApiResult.success();
    }

}
