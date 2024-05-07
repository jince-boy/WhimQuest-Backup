package com.whim.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.whim.common.core.constant.RedisKeyConstants;
import com.whim.common.exception.CheckVerifyCodeException;
import com.whim.common.exception.UserNotFoundException;
import com.whim.common.exception.UserPasswordNotMatchException;
import com.whim.common.utils.BCryptPasswordEncoderUtils;
import com.whim.common.utils.CryptoUtils;
import com.whim.common.utils.IpUtils;
import com.whim.common.utils.RedisCacheHelper;
import com.whim.common.utils.StringFormatUtils;
import com.whim.common.web.JwtVO;
import com.whim.model.dto.admin.user.UserLoginDTO;
import com.whim.model.entity.SysUser;
import com.whim.model.vo.admin.user.SysPermissionVO;
import com.whim.model.vo.admin.user.SysUserInfoVO;
import com.whim.service.IAuthService;
import com.whim.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Jince
 * @since: 2023.12.17 上午 12:09
 * @description: 认证业务类
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final RedisCacheHelper redisCacheHelper;
    private final ISysUserService userService;


    /**
     * 用户登录
     * UserLoginDTO 用户登录对象
     *
     * @return jwtVo对象
     */
    public JwtVO login(UserLoginDTO userLoginDTO) {
        Boolean isExist = redisCacheHelper.hasKey(StringFormatUtils.format(RedisKeyConstants.VERIFY_CODE_KEY, IpUtils.getIpAddress(), userLoginDTO.getCode().toLowerCase()));
        if (isExist) {
            //验证成功之后从缓存中删除验证码
            redisCacheHelper.deleteObject(StringFormatUtils.format(RedisKeyConstants.VERIFY_CODE_KEY, IpUtils.getIpAddress(), userLoginDTO.getCode().toLowerCase()));
        } else {
            log.info("验证码错误");
            throw new CheckVerifyCodeException("验证码错误");
        }
        SysUser sysUser = userService.selectUserByUserName(userLoginDTO.getUsername().trim());
        if (Objects.isNull(sysUser)) {
            throw new UserNotFoundException("你登录的账户不存在");
        }
        if (BCryptPasswordEncoderUtils.matches(userLoginDTO.getPassword(), sysUser.getPassword())) {
            if (userLoginDTO.getRememberMe()) {
                StpUtil.login(sysUser.getId());
            } else {
                StpUtil.login(sysUser.getId(), false);
            }
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return new JwtVO(tokenInfo.getTokenValue(), tokenInfo.getTokenTimeout());
        }
        throw new UserPasswordNotMatchException("用户名或密码错误");
    }

    /**
     * 用户退出登陆
     *
     * @return true退出成功, false退出失败
     */
    public Boolean logout() {
        try {
            StpUtil.logout();
            return true;
        } catch (Exception exception) {
            log.info("用户退出登录失败:{}", exception.getMessage());
            return false;
        }
    }

    /**
     * 获取图片验证码
     *
     * @return 图片验证码Base64字符串 3分钟
     */
    @Override
    public String getVerifyCode() {
        CryptoUtils.VerifyCodeBase64 verifyCodeBase64 = CryptoUtils.builder().getVerifyCodeBase64();
        redisCacheHelper.setObject(StringFormatUtils.format(RedisKeyConstants.VERIFY_CODE_KEY, IpUtils.getIpAddress(), verifyCodeBase64.getText()), verifyCodeBase64.getText(), 3L, TimeUnit.MINUTES);
        return verifyCodeBase64.getBase64();
    }

    /**
     * 获取当前用户信息包含权限角色列表
     *
     * @return SysUserInfoVO
     */
    @Override
    public SysUserInfoVO getUserInfo() {
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        return userService.selectUserInfoById(loginIdAsLong);
    }
}
