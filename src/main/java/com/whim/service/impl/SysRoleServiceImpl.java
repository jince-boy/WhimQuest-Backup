package com.whim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whim.mapper.SysRoleMapper;
import com.whim.model.entity.SysRole;
import com.whim.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author jince
 * @since 2024-01-03 10:34:58
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private final SysRoleMapper sysRoleMapper;

    /**
     * 通过用户id获取角色Code列表
     *
     * @param userId 用户id
     * @return 角色Code列表
     */
    @Override
    @Transactional
    public List<String> getRoleByUserId(Long userId) {
        return sysRoleMapper.getRoleByUserId(userId);
    }
}

