package com.whim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whim.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author jince
 * @since 2024-01-03 10:34:57
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户id获取角色列表
     * @param userId 用户id
     * @return 角色列表
     */
    List<String> getRoleByUserId(@Param("userId") Long userId);
}

