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
 * 系统用户表(SysUser)实体类
 *
 * @author jince
 * @since 2024-01-02 10:53:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 276629475778879132L;
    /**
     * 主键id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别：1男 2女
     */
    private String gender;
    /**
     * 用户状态 0开启,1关闭
     */
    private String status;
}
