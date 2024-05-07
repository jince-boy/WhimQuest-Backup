package com.whim.model.dto.admin.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Jince
 * @since: 2023.12.17 下午 07:04
 * @description: 用户登录DTO
 */
@Data
@Schema(name = "用户登陆对象", description = "用户登陆对象")
public class UserLoginDTO {
    @NotNull(message = "用户名不能为空")
    @Size(min = 5, max = 18, message = "用户名长度在5位到18位之间")
    @Schema(description = "用户名")
    private String username;
    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 18, message = "密码长度在6位到18位之间")
    @Schema(description = "密码")
    private String password;
    @NotNull(message = "验证码不能为空")
    @Size(min = 4, max = 4, message = "验证码长度为4位")
    @Schema(description = "验证码")
    private String code;
    @NotNull(message = "记住我不能为空")
    @Schema(description = "记住我")
    private Boolean rememberMe;
}
