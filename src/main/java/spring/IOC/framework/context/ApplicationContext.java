package spring.IOC.framework.context;

import spring.IOC.framework.beans.factory.BeanFactory;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName ApplicationContext.java
 * @Description 定义非延时加载功能
 * @createTime 2021年10月26日 16:39:00
 */
public interface ApplicationContext extends BeanFactory {

    void refresh() throws Exception;
}
