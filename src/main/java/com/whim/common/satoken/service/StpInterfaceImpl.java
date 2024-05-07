package com.whim.common.satoken.service;

import cn.dev33.satoken.stp.StpInterface;
import com.whim.service.ISysPermissionService;
import com.whim.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jince
 * @since: 2024.01.15 下午 05:19
 * @description: saToken权限认证
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StpInterfaceImpl implements StpInterface {
    private final ISysPermissionService permissionService;
    private final ISysRoleService roleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return permissionService.selectPermissionCodeByUserId(1L);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return roleService.getRoleByUserId(Long.valueOf(loginId.toString()));
    }
}
