package com.whim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whim.mapper.SysUserMapper;
import com.whim.model.entity.SysUser;
import com.whim.model.vo.admin.user.SysUserInfoVO;
import com.whim.service.ISysPermissionService;
import com.whim.service.ISysRoleService;
import com.whim.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author Jince
 * @since 2023-12-16 23:52:04
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final ISysRoleService roleService;
    private final ISysPermissionService permissionService;

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    @Override
    @Transactional
    public SysUser selectUserByUserName(String username) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(username), SysUser::getUsername, username);
        return this.getOne(lambdaQueryWrapper);
    }

    /**
     * 通过用户id查询用户信息
     *
     * @param userId id
     * @return SysUser
     */
    @Override
    @Transactional
    public SysUser selectUserById(Long userId) {
        return this.getById(userId);
    }

    /**
     * 通过用户id查询用户详细信息,包含角色权限
     *
     * @param userId 用户id
     * @return SysUserInfoVO
     */
    @Override
    @Transactional
    public SysUserInfoVO selectUserInfoById(Long userId) {
        SysUserInfoVO sysUserInfoVO = new SysUserInfoVO();
        BeanUtils.copyProperties(this.getById(userId), sysUserInfoVO);
        sysUserInfoVO.setPermissions(permissionService.selectPermissionCodeByUserId(userId));
        sysUserInfoVO.setRoles(roleService.getRoleByUserId(userId));
        sysUserInfoVO.setMenus(permissionService.selectMenuByUserId(userId));
        return sysUserInfoVO;
    }
}

