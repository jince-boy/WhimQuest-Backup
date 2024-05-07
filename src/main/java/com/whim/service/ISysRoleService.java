package com.whim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whim.model.entity.SysRole;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author jince
 * @since 2024-01-03 10:34:58
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 通过用户id获取角色Code列表
     *
     * @param userId 用户id
     * @return 角色Code列表
     */
    List<String> getRoleByUserId(Long userId);

}
