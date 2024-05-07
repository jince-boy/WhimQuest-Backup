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
 * 角色表(SysRole)实体类
 *
 * @author jince
 * @since 2024-01-03 10:34:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -14438624922022828L;
    /**
     * 主键id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private String status;
}
