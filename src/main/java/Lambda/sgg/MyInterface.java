package Lambda.sgg;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName MyInterface.java
 * @Description 自定义函数式接口
 * @createTime 2021年09月21日 15:21:00
 */

// 这个注解加不加都是函数式接口，注解起到一个检测的作用

@FunctionalInterface
public interface MyInterface {

    void  method1();
}
