package com.whim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whim.common.exception.ParamException;
import com.whim.common.exception.ServiceException;
import com.whim.mapper.SysPermissionMapper;
import com.whim.model.dto.admin.permission.AddPermissionDTO;
import com.whim.model.dto.admin.permission.DeletePermissionDTO;
import com.whim.model.dto.admin.permission.UpdatePermissionDTO;
import com.whim.model.entity.SysPermission;
import com.whim.model.vo.admin.user.SysPermissionVO;
import com.whim.service.ISysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 权限菜单表(SysPermission)表服务实现类
 *
 * @author jince
 * @since 2024-01-03 10:58:21
 */
@Service
@RequiredArgsConstructor
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    private final SysPermissionMapper sysPermissionMapper;

    /**
     * 通过用户id获取权限列表
     *
     * @param userId 用户id
     * @return 权限列表
     */
    @Override
    @Transactional
    public List<String> selectPermissionCodeByUserId(Long userId) {
        return sysPermissionMapper.getPermissionByUserId(userId);
    }

    /**
     * 获取菜单列表
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    @Override
    @Transactional
    public List<SysPermissionVO> selectMenuByUserId(Long userId) {
        // 获取用户的菜单列表
        List<SysPermissionVO> menuListByUserId = sysPermissionMapper.getMenuByUserId(userId);
        // 使用 Map 构建菜单项的子菜单索引
        Map<Long, List<SysPermissionVO>> childrenMap = new HashMap<>();
        // 存储根节点的列表
        List<SysPermissionVO> rootNodes = new ArrayList<>();
        // 遍历菜单列表，构建菜单树
        for (SysPermissionVO sysPermissionVO : menuListByUserId) {
            if (sysPermissionVO.getParentId() == 0) {
                // 将没有父菜单的菜单项添加到根节点列表中
                rootNodes.add(sysPermissionVO);
            } else {
                // 使用父菜单ID作为键，将子菜单添加到对应的子菜单列表中
                childrenMap
                        .computeIfAbsent(sysPermissionVO.getParentId(), k -> new ArrayList<>())
                        .add(sysPermissionVO);
            }
        }
        // 为根节点递归设置子菜单
        for (SysPermissionVO sysPermissionVO : rootNodes) {
            sysPermissionVO.setChildren(getChildren(sysPermissionVO.getId(), childrenMap));
        }
        return rootNodes;
    }

    /**
     * 递归获取子菜单
     *
     * @param parentId    父id
     * @param childrenMap 子菜单
     * @return 子菜单列表
     */
    private List<SysPermissionVO> getChildren(Long parentId, Map<Long, List<SysPermissionVO>> childrenMap) {
        List<SysPermissionVO> children = childrenMap.get(parentId);
        if (children != null) {
            // 为当前菜单项设置子菜单
            for (SysPermissionVO sysPermissionVO : children) {
                sysPermissionVO.setChildren(getChildren(sysPermissionVO.getId(), childrenMap));
            }
        }
        return children;
    }


    /**
     * 添加权限
     *
     * @param addPermissionDTO 添加权限DTO
     * @return true添加成功, false添加失败
     */
    @Override
    @Transactional
    public Boolean addPermission(AddPermissionDTO addPermissionDTO) {
        // 查找权限标识
        LambdaQueryWrapper<SysPermission> sysPermissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysPermissionLambdaQueryWrapper.eq(SysPermission::getPermissionCode, addPermissionDTO.getPermissionCode());
        // 判断当前的权限标识是否存在
        if (this.exists(sysPermissionLambdaQueryWrapper)) {
            throw new ParamException("当前权限标识已存在,请更换权限标识");
        }
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(addPermissionDTO, sysPermission);
        return this.save(sysPermission);
    }

    /**
     * 修改权限
     *
     * @param updatePermissionDTO 修改权限对象
     * @return true修改成功, false修改失败
     */
    @Override
    @Transactional
    public Boolean updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        // 查找权限标识
        LambdaQueryWrapper<SysPermission> sysPermissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysPermissionLambdaQueryWrapper.eq(SysPermission::getPermissionCode, updatePermissionDTO.getPermissionCode());
        // 选择的上级id不能是自己
        if (updatePermissionDTO.getParentId() != null && updatePermissionDTO.getParentId().equals(updatePermissionDTO.getId())) {
            throw new ParamException("失败,上级不能选择自己");
        }
        // 查询权限标识是否是被别人使用的,如果是就拒绝,不是就通过
        if (this.exists(sysPermissionLambdaQueryWrapper)) {
            SysPermission permission = this.getOne(sysPermissionLambdaQueryWrapper);
            if (! Objects.equals(permission.getId(), updatePermissionDTO.getId())) {
                throw new ParamException("当前权限标识已存在,请更换权限标识");
            }
        }
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(updatePermissionDTO, sysPermission);
        return this.updateById(sysPermission);
    }

    /**
     * 删除权限
     *
     * @param deletePermissionDTO 删除权限对象
     * @return true删除成功, false删除失败
     */
    @Override
    @Transactional
    public Boolean deletePermission(DeletePermissionDTO deletePermissionDTO) {
        // 查询当前删除id下是否存在子权限
        for (Long id : deletePermissionDTO.getIds()) {
            LambdaQueryWrapper<SysPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysPermission::getParentId, id);
            if (this.exists(lambdaQueryWrapper)) {
                throw new ServiceException("删除失败,删除的权限下有子权限");
            }
        }
        // 删除角色权限中间表内容
        for (Long id : deletePermissionDTO.getIds()) {
            sysPermissionMapper.deleteRolePermissionByPermissionId(id);
        }
        return this.removeByIds(deletePermissionDTO.getIds());
    }
}

