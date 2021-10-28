package spring.IOC.framework.beans.factory;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName BeanFactory.java
 * @Description IOC 容器父接口
 * @createTime 2021年10月26日 16:19:00
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;

    <T> T getBean(String name, Class<? extends T> clazz) throws Exception;
}
