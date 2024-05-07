package com.whim.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.whim.common.core.annotation.BigDecimalFormat;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/11/18 15:52
 * @description: BigDecimal小数点序列化
 */
@JsonComponent
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {
    //格式化BigDecimal类型的小数位数(默认)
    private String format = "#####0.00";


    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(new DecimalFormat(format).format(bigDecimal));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        //当前字段不为空
        if (beanProperty != null) {
            //如果当前字段类型为BigDecimal类型则处理,否则跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), BigDecimal.class)) {
                //获取当前字段的自定义注解
                BigDecimalFormat annotation = beanProperty.getAnnotation(BigDecimalFormat.class);
                //没有打自定义注解,获取上下文中的自定义注解
                if (annotation == null) {
                    annotation = beanProperty.getContextAnnotation(BigDecimalFormat.class);
                }
                BigDecimalSerializer bigDecimalSerializer = new BigDecimalSerializer();
                StringBuilder tmp = new StringBuilder("#####0.");
                //打了自定义注解的字段
                if (annotation != null) {
                    //获取想保留的小数位数
                    tmp.append("0".repeat(Math.max(0, annotation.precision())));
                    bigDecimalSerializer.format = tmp.toString();
                }
                return bigDecimalSerializer;
            }
            return serializerProvider.findContentValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }

}
