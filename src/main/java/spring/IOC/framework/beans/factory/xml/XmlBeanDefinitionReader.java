package spring.IOC.framework.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import spring.IOC.framework.beans.BeanDefinition;
import spring.IOC.framework.beans.MutablePropertyValues;
import spring.IOC.framework.beans.PropertyValue;
import spring.IOC.framework.beans.factory.support.BeanDefinitionReader;
import spring.IOC.framework.beans.factory.support.BeanDefinitionRegistry;
import spring.IOC.framework.beans.factory.support.SimpleBeanDefinitionRegistry;

import java.io.InputStream;
import java.util.List;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName XmlBeanDefinitionReader.java
 * @Description 针对 xml 配置文件进行解析的类
 * @createTime 2021年10月26日 13:48:00
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    // 声明注册表对象
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(){
        registry = new SimpleBeanDefinitionRegistry();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinition(String configLocation) throws Exception {
        // 使用 dom4j 进行 xml 配置文件的解析
        SAXReader reader = new SAXReader();
        // 获取类路径下的配置文件
        InputStream is = XmlBeanDefinitionReader.class.getClassLoader().getResourceAsStream(configLocation);
        Document document = reader.read(is);

        // 根据 Document 对象获取根标签对象
        Element rootElement = document.getRootElement();
        // 获取根标签下所有的 bean 标签对象
        List<Element> beanElements = rootElement.elements("bean");

        // 遍历集合
        for (Element beanElement : beanElements) {
            // 获取 id 属性
            String id = beanElement.attributeValue("id");

            // 获取 class 属性
            String className = beanElement.attributeValue("class");


            // 将 id属性 和 class属性 封装到 BeanDefinition 对象中
            // 1.创建BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);


            // 创建 MutablePropertyValues 对象
            MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();

            // 获取 bean 标签下所有的 property 标签对象
            List<Element> propertyElements = beanElement.elements("property");
            for (Element propertyElement : propertyElements) {
                String name = propertyElement.attributeValue("name");
                String ref = propertyElement.attributeValue("ref");
                String value = propertyElement.attributeValue("value");
                PropertyValue propertyValue = new PropertyValue(name,ref,value);
                mutablePropertyValues.addPropertyValue(propertyValue);
            }

            // 将 MutablePropertyValues 封装到 beanDefinition 对象中
            beanDefinition.setPropertyValues(mutablePropertyValues);

            // 将beanDefinition 对象注册到注册表中
            registry.registerBeanDefinition(id,beanDefinition);
        }
    }
}
