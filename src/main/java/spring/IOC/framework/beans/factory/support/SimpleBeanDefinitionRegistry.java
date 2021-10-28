package spring.IOC.framework.beans.factory.support;

import spring.IOC.framework.beans.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName SimpleBeanDefinitionRegistry.java
 * @Description TODO
 * @createTime 2021年10月26日 13:38:00
 */
public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {

    // 定义一个容器，用来存储 BeanDefinition 对象
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) throws Exception {
        beanDefinitionMap.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws Exception {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
