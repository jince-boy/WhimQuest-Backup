package com.whim.model.entity;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whim.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 权限菜单表(SysPermission)实体类
 *
 * @author jince
 * @since 2024-01-03 11:02:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermission extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -90383661558233969L;
    /**
     * 主键id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 权限/菜单名称
     */
    private String name;
    /**
     * 父节点id
     */
    private Long parentId;
    /**
     * 菜单类型(1.菜单 2目录 3外链 4按钮)
     */
    private String type;
    /**
     * 前端路由路径(浏览器地址栏路径)
     */
    private String path;
    /**
     * 前端组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 权限标识
     */
    private String permissionCode;
    /**
     * 显示状态1可见0隐藏
     */
    private String visible;
    /**
     * 跳转路径
     */
    private String redirectPath;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 备注
     */
    private String remark;
}
