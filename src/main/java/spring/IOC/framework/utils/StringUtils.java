package spring.IOC.framework.utils;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName StringUtils.java
 * @Description TODO
 * @createTime 2021年10月26日 20:13:00
 */
public class StringUtils {
    public static String getSetterMethodByFieldName(String fieldName){
        String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return methodName;
    }
}
