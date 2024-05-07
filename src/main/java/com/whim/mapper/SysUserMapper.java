package com.whim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whim.model.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户表(SysUser)表数据库访问层
 *
 * @author Jince
 * @since 2023-12-16 23:52:03
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}