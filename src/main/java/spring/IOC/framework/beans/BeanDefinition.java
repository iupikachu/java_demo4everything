package spring.IOC.framework.beans;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName BeanDefinition.java
 * @Description 用来封装bean标签数据
 *      id 属性
 *      class 属性
 *      property 子标签的数据
 * @createTime 2021年10月26日 10:56:00
 */
public class BeanDefinition {
    private String id;
    private String className;

    private MutablePropertyValues propertyValues;

    public BeanDefinition() {
        propertyValues = new MutablePropertyValues();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
