package com.whim.model.vo.admin.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


/**
 * @author Jince
 * @since: 2024.02.27 上午 10:37
 * @description: 权限菜单列表
 */
@Data
public class SysPermissionVO {
    /**
     * 主键id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;
    /**
     * 权限/菜单名称
     */
    @Schema(description = "权限/菜单名称")
    private String name;
    /**
     * 父节点id
     */
    @Schema(description = "父节点id")
    private Long parentId;
    /**
     * 菜单类型(1.菜单 2目录 3外链 4按钮)
     */
    @Schema(description = "菜单类型(1.菜单 2目录 3外链 4按钮)")
    private String type;
    /**
     * 前端路由路径(浏览器地址栏路径)
     */
    @Schema(description = "前端路由路径(浏览器地址栏路径)")
    private String path;
    /**
     * 前端组件路径(vue页面完整路径，省略.vue后缀)
     */
    @Schema(description = "前端组件路径(vue页面完整路径，省略.vue后缀)")
    private String component;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String permissionCode;
    /**
     * 显示状态1可见0隐藏
     */
    @Schema(description = "显示状态1可见0隐藏")
    private String visible;
    /**
     * 跳转路径
     */
    @Schema(description = "跳转路径")
    private String redirectPath;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    /**
     * 子菜单
     */
    @Schema(description = "子菜单")
    private List<SysPermissionVO> children;
}
