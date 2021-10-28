package spring.IOC.framework.beans.factory.support;

import spring.IOC.framework.beans.BeanDefinition;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName BeanDefinitionRegistry.java
 * @Description 注册表对象
 * @createTime 2021年10月26日 13:33:00
 */
public interface BeanDefinitionRegistry {
    // 注册 BeanDefinition 对象到注册表中
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    // 从注册表中删除指定名称的 BeanDefinition 对象
    void removeBeanDefinition(String beanName) throws Exception;

    // 根据名称从注册表中获取 BeanDefinition 对象
    BeanDefinition getBeanDefinition(String beanName) throws Exception;

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();
}
