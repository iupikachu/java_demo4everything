package spring.IOC.framework.beans.factory.support;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName BeanDefinitionReader.java
 * @Description 解析配置文件
 * @createTime 2021年10月26日 13:46:00
 */
public interface BeanDefinitionReader {
    // 获取注册表对象
    BeanDefinitionRegistry getRegistry();

    // 加载配置文件并在注册表中进行注册
    void loadBeanDefinition(String configLocation) throws Exception;

}
