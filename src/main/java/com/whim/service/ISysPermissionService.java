package com.whim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whim.model.dto.admin.permission.AddPermissionDTO;
import com.whim.model.dto.admin.permission.DeletePermissionDTO;
import com.whim.model.dto.admin.permission.UpdatePermissionDTO;
import com.whim.model.entity.SysPermission;
import com.whim.model.vo.admin.user.SysPermissionVO;

import java.util.List;

/**
 * 权限菜单表(SysPermission)表服务接口
 *
 * @author jince
 * @since 2024-01-03 10:58:21
 */
public interface ISysPermissionService extends IService<SysPermission> {
    /**
     * 通过用户id获取权限列表
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<String> selectPermissionCodeByUserId(Long userId);

    /**
     * 通过用户id获取菜单列表
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    List<SysPermissionVO> selectMenuByUserId(Long userId);

    /**
     * 添加权限
     *
     * @param addPermissionDTO 添加权限DTO
     * @return true添加成功, false添加失败
     */
    Boolean addPermission(AddPermissionDTO addPermissionDTO);

    /**
     * 修改权限
     *
     * @param updatePermissionDTO 修改权限对象
     * @return true修改成功, false修改失败
     */
    Boolean updatePermission(UpdatePermissionDTO updatePermissionDTO);

    /**
     * 删除权限
     *
     * @param deletePermissionDTO 删除权限对象
     * @return true删除成功, false删除失败
     */
    Boolean deletePermission(DeletePermissionDTO deletePermissionDTO);
}
