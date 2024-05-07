package com.whim.service;

import com.whim.common.web.JwtVO;
import com.whim.model.dto.admin.user.UserLoginDTO;
import com.whim.model.vo.admin.user.SysUserInfoVO;

/**
 * @author Jince
 * @since: 2023.12.17 上午 12:09
 * @description: 认证业务类接口
 */
public interface IAuthService {
    /**
     * 用户登录
     * UserLoginDTO 用户登录对象
     *
     * @return jwtVo对象
     */
    JwtVO login(UserLoginDTO userLoginDTO);

    /**
     * 用户退出登陆
     *
     * @return true退出成功, false退出失败
     */
    Boolean logout();

    /**
     * 获取图片验证码
     *
     * @return 图片验证码Base64字符串
     */
    String getVerifyCode();

    /**
     * 获取当前用户信息包含权限和角色
     */
    SysUserInfoVO getUserInfo();
}
