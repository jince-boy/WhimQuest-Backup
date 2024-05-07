package com.whim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whim.model.entity.SysUser;
import com.whim.model.vo.admin.user.SysUserInfoVO;

/**
 * 系统用户表(SysUser)表服务接口
 *
 * @author Jince
 * @since 2023-12-16 23:52:04
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return SysUser
     */
    SysUser selectUserByUserName(String username);
    /**
     * 通过用户id查询用户信息
     * @param userId id
     * @return SysUser
     */
    SysUser selectUserById(Long userId);

    /**
     * 通过用户id查询用户详细信息,包含角色权限
     * @param userId 用户id
     * @return SysUserInfoVO
     */
    SysUserInfoVO selectUserInfoById(Long userId);
}
