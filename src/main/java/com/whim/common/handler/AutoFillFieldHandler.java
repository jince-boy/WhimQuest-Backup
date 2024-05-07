package com.whim.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.whim.common.core.base.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Jince
 * @since: 2023.11.30 上午 02:09
 * @description: 自动填充处理器
 */
public class AutoFillFieldHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
            if (Objects.isNull(baseEntity.getCreateTime())) {
                baseEntity.setCreateTime(LocalDateTime.now());
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
            if (Objects.isNull(baseEntity.getUpdateTime())) {
                baseEntity.setUpdateTime(LocalDateTime.now());
            }
        }
    }
}
