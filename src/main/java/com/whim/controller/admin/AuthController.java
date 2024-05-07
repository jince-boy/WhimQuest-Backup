package com.whim.controller.admin;

import com.whim.common.web.JwtVO;
import com.whim.common.web.Result;
import com.whim.model.dto.admin.user.UserLoginDTO;
import com.whim.model.vo.admin.user.SysUserInfoVO;
import com.whim.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jince
 * @since: 2023.12.17 上午 12:08
 * @description: admin 认证控制器
 */
@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
@Tag(name = "用户认证")
public class AuthController {

    private final IAuthService authService;

    /**
     * 用户登陆
     */
    @PostMapping("/login")
    @Operation(summary = "用户登陆")
    public Result<JwtVO> login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return Result.success("登录成功", authService.login(userLoginDTO));
    }

    /**
     * 用户退出
     */
    @PostMapping("/logout")
    @Operation(summary = "用户退出")
    public Result<String> logout() {
        if (authService.logout()) {
            return Result.success("退出成功");
        }
        return Result.error("退出失败,服务器错误");
    }

    /**
     * 获取后端登录验证码
     */
    @Operation(summary = "获取验证码")
    @GetMapping("/verifyCode")
    public Result<String> getVerifyCode() {
        return Result.success("获取成功", authService.getVerifyCode());
    }

    /**
     * 获取个人信息
     */
    @Operation(summary = "获取当前用户信息")
    @GetMapping("/getUserInfo")
    public Result<SysUserInfoVO> getUserInfo() {
        return Result.success("获取成功", authService.getUserInfo());
    }
}
