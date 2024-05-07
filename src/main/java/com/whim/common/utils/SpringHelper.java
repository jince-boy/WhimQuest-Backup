package com.whim.common.utils;

import lombok.Getter;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Jince
 * @since: 2023.12.24 上午 01:22
 * @description: spring工具类 方便在非spring管理环境中获取bean
 */
@Component
public class SpringHelper implements BeanFactoryPostProcessor, ApplicationContextAware {
    private static ConfigurableListableBeanFactory beanFactory;

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringHelper.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringHelper.applicationContext = applicationContext;
    }

    public static ListableBeanFactory getBeanFactory() {
        return null == beanFactory ? applicationContext : beanFactory;
    }

    /**
     * 获取对象
     *
     * @param name bean name
     * @return Object 一个以所给名字注册的bean的实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) getBeanFactory().getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clazz 类
     * @return Object 一个以所给类注册的bean的实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return getBeanFactory().getBean(clazz);
    }

    /**
     * @param name  bean name
     * @param clazz clazz
     * @param <T>   Object 一个以所给名字和类注册的bean的实例
     * @return Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getBeanFactory().getBean(name, clazz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name bean name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param name bean name
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        return getBeanFactory().isSingleton(name);
    }

    /**
     * @param name bean name
     * @return Class 注册对象的类型
     */
    public static Class<?> getType(String name) {
        return getBeanFactory().getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name bean name
     * @return String[]
     */
    public static String[] getAliases(String name) {
        return getBeanFactory().getAliases(name);
    }

    /**
     * 获取aop代理对象
     *
     * @param invoker 对象
     * @return aop代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }

    /**
     * 获取当前的环境配置，无配置返回null
     *
     * @return 当前的环境配置
     */
    public static String[] getActiveProfiles() {
        return getApplicationContext().getEnvironment().getActiveProfiles();
    }

    /**
     * 获取配置文件中的值
     *
     * @param key 配置文件的key
     * @return 当前的配置文件的值
     */
    public static String getRequiredProperty(String key) {
        return getApplicationContext().getEnvironment().getRequiredProperty(key);
    }
}
