package com.whim.model.dto.admin.permission;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author: Administrator-CCENOTE
 * @since: 2024-01-10 17:43
 * @description: 添加权限DTO
 */
@Data
@Schema(name = "权限添加对象", description = "权限添加对象")
public class AddPermissionDTO {
    /**
     * 权限/菜单名称
     */
    @NotNull(message = "权限名称不能为空")
    @Size(min = 2, message = "权限名称长度不能少于两位")
    @Schema(description = "权限/菜单名称")
    private String name;
    /**
     * 父节点id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "父节点id")
    private Long parentId;
    /**
     * 菜单类型(1.菜单 2.目录 3.外链 4.按钮)
     */
    @NotNull(message = "菜单类型不能为空")
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
    @NotNull(message = "排序不能为空")
    @Max(value = 9999,message = "最大为4位整数")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 权限标识
     */
    @NotNull(message = "权限标识不能为空")
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
}
