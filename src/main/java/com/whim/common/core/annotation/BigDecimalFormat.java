package com.whim.common.core.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.whim.common.serializer.BigDecimalSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jince
 * @since: 2023.12.15 下午 08:14
 * @description: 自定义注解，限制BigDecimal的小数点位数
 */
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
@Target(ElementType.FIELD) // 作用于字段
@JacksonAnnotationsInside
@JsonSerialize(using = BigDecimalSerializer.class)
public @interface BigDecimalFormat {
    int precision() default 2;
}
