package spring.IOC.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName MutablePropertyValues.java
 * @Description 用来存储和管理多个 PropertyValue 对象
 * @createTime 2021年10月26日 09:41:00
 */
public class MutablePropertyValues implements Iterable<PropertyValue>{

    // 定义 list 集合对象，用来存储 PropertyValue 对象
    private final List<PropertyValue> propertyValueList;

    public MutablePropertyValues() {
        this.propertyValueList = new ArrayList<PropertyValue>();
    }

    public MutablePropertyValues(List<PropertyValue> propertyValueList) {
        if( propertyValueList == null){
            this.propertyValueList = new ArrayList<PropertyValue>();
        }else {
            this.propertyValueList = propertyValueList;
        }
    }

    // 获取所有的 Propertyvalue 对象，返回以数组的形式
    public PropertyValue[] getPropertyValues(){
        // 将集合转换为数组
        return (PropertyValue[]) propertyValueList.toArray();
    }

    // 根据 name 属性值获取 PropertyValue 对象
    public PropertyValue getPropertyValue(String propertyName){
        // 遍历集合对象
        for (PropertyValue propertyValue : propertyValueList) {
            if(propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }

    // 判断集合是否为空
    public boolean isEmpty(){
        return propertyValueList.isEmpty();
    }

    // 添加 PropertyValue 对象
    public MutablePropertyValues addPropertyValue(PropertyValue pv){
        // 判断集合中存储的 PropertyValue 对象是否和传递进行的重复了，如果重复了，进行覆盖
        for (int i = 0; i < propertyValueList.size(); i++) {
            // 获取集合中每一个PropertyValue 对象
            PropertyValue currentPv = propertyValueList.get(i);
            if(currentPv.getName().equals(pv.getName())){
                propertyValueList.set(i,pv);
                return this; // 目的就是实现链式编程
            }
        }
        this.propertyValueList.add(pv);
        return this; // 目的就是实现链式编程
    }

    // 判断是否有指定name属性值的对象
    public boolean contains(String propertyName){
        return getPropertyValue(propertyName) != null;
    }

    // 获取迭代器对象
    @Override
    public Iterator<PropertyValue> iterator() {
        return propertyValueList.iterator();
    }
}
