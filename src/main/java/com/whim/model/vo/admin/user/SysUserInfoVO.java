package com.whim.model.vo.admin.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author: Administrator-CCENOTE
 * @since: 2024-01-09 9:16
 * @description: 用户信息VO
 */
@Data
public class SysUserInfoVO {
    /**
     * 主键id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String avatar;
    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String mobile;
    /**
     * 性别：1男 2女
     */
    @Schema(description = "性别：1男 2女")
    private String gender;
    /**
     * 用户状态 0开启,1关闭
     */
    @Schema(description = "用户状态 0开启,1关闭")
    private String status;
    /**
     * 权限列表
     */
    @Schema(description = "权限列表")
    private List<String> permissions;
    /**
     * 角色列表
     */
    @Schema(description = "角色列表")
    private List<String> roles;
    /**
     * 菜单列表
     */
    @Schema(description = "菜单列表")
    private List<SysPermissionVO> menus;
}
