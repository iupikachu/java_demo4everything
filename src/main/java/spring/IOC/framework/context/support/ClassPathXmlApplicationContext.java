package spring.IOC.framework.context.support;

import spring.IOC.framework.beans.BeanDefinition;
import spring.IOC.framework.beans.MutablePropertyValues;
import spring.IOC.framework.beans.PropertyValue;
import spring.IOC.framework.beans.factory.support.BeanDefinitionRegistry;
import spring.IOC.framework.beans.factory.xml.XmlBeanDefinitionReader;
import spring.IOC.framework.utils.StringUtils;

import java.lang.reflect.Method;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName ClassPathXmlApplicationContext.java
 * @Description  IOC 容器具体的子实现类
 *               用于加载类路径下的 xml 格式的配置文件
 * @createTime 2021年10月26日 19:32:00
 */
public  class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configLocation){
        this.configLocation = configLocation;
        beanDefinitionReader = new XmlBeanDefinitionReader();
        try{
            this.refresh();
        }catch (Exception e){

        }
    }

    // 根据 bean 对象的名称获取 bean 对象
    @Override
    public Object getBean(String name) throws Exception {
        // 判断对象容器中是否包含指定名称的 bean 对象，如果包含，直接返回即可，如果不包含，需要自行创建
        Object obj = singletonObjects.get(name);
        if(obj != null){
            return obj;
        }
        // 获取 BeanDefinition 对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        BeanDefinition beanDefinition = registry.getBeanDefinition(name);
        // 获取 bean 信息中的 className
        String className = beanDefinition.getClassName();
        // 通过反射创建对象
        Class<?> clazz = Class.forName(className);
        Object beanObj = clazz.newInstance();

        // 进行依赖注入操作
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            // 获取 name 属性
            String propertyName = propertyValue.getName();
            // 获取 value 属性
            String value = propertyValue.getValue();
            // 获取 ref 属性
            String ref = propertyValue.getRef();

            if(ref != null && !ref.equals("")){
                // 获取依赖的 bean 对象
                Object bean = getBean(ref);
                // 拼接方法名
                String methodName = StringUtils.getSetterMethodByFieldName(propertyName);
                // 反射获取所有的方法对象
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(methodName.equals(method.getName())){
                        // 执行该 setter 方法
                        method.invoke(beanObj,bean);
                    }
                }
            }

            if(value != null && !"".equals(value)){
                // 拼接方法名
                String methodName = StringUtils.getSetterMethodByFieldName(propertyName);
                // 获取 method 对象
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(beanObj,value);

            }
        }

        // 在返回 beanObj 对象之前，将该对象存储到 map 容器中
        singletonObjects.put(name,beanObj);
        return beanObj;
    }

    @Override
    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {
        Object bean = getBean(name);
        if(bean == null){
            return null;
        }
        return clazz.cast(bean);
    }
}
