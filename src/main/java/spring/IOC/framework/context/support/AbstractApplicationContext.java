package spring.IOC.framework.context.support;

import spring.IOC.framework.beans.factory.support.BeanDefinitionReader;
import spring.IOC.framework.beans.factory.support.BeanDefinitionRegistry;
import spring.IOC.framework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName AbstractApplicationContext.java
 * @Description ApplicationContext 接口的子实现类，用于立即加载
 * @createTime 2021年10月26日 16:44:00
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    // 声明解析器变量
    protected BeanDefinitionReader beanDefinitionReader;

    // 定义用于存储 bean 对象的 map 容器
    protected Map<String, Object> singletonObjects = new HashMap<String, Object>();

    // 声明配置文件路径的变量
    protected String configLocation;

    @Override
    public void refresh() throws Exception {
        // 加载 BeanDefinition 对象
        beanDefinitionReader.loadBeanDefinition(configLocation);
        // 初始化bean
        finishBeanInitialization();

    }

    // bean 的初始化
    private void finishBeanInitialization() throws Exception{
        // 获取注册表对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();

        // 获取 BeanDefinition 对象
        String[] beanNames = registry.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            // 进行 bean 的初始化
            getBean(beanName);
        }
    }
}
