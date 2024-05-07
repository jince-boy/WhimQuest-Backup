package com.whim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whim.model.entity.SysPermission;
import com.whim.model.vo.admin.user.SysPermissionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限菜单表(SysPermission)表数据库访问层
 *
 * @author jince
 * @since 2024-01-03 10:58:21
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 通过用户id获取权限列表
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<String> getPermissionByUserId(@Param("userId") Long userId);

    /**
     * 通过用户id获取菜单列表
     *
     * @param userId 用户Id
     * @return 菜单列表
     */
    List<SysPermissionVO> getMenuByUserId(@Param("userId") Long userId);

    /**
     * 删除角色权限中间表
     *
     * @param permissionId 权限id
     * @return true删除成功 false删除失败
     */
    Boolean deleteRolePermissionByPermissionId(@Param("permissionId") Long permissionId);
}

