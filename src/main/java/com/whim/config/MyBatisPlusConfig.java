package com.whim.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.whim.common.handler.AutoFillFieldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jince
 * @since: 2023.11.30 上午 02:06
 * @description: mybatis plus 配置类
 */
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor getMybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 防全表更新与删除插件
        mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return mybatisPlusInterceptor;
    }
    @Bean
    public MetaObjectHandler AutoFillFieldHandler() {
        return new AutoFillFieldHandler();
    }
}
