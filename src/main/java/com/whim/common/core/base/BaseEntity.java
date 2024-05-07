package com.whim.common.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jince
 * @since: 2023.11.30 上午 02:01
 * @description: 实体类基类
 */
@Setter
@Data
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}
