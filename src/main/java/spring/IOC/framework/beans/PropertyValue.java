package spring.IOC.framework.beans;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName PropertyValue.java
 * @Description 用来封装bean标签下的 property 标签的属性
 *              name 属性
 *              ref 属性
 *              value 属性 ： 给基本数据类型及 String 类型数据赋值
 *
 * @createTime 2021年10月26日 09:35:00
 */
public class PropertyValue {

    private String name;
    private String ref;
    private String value;

    public PropertyValue() {
    }

    public PropertyValue(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
