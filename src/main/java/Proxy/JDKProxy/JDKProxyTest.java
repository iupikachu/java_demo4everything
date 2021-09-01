package Proxy.JDKProxy;

import Proxy.Car;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName JDKProxyTest.java
 * @Description
 *
 * loader 类加载器
 * interfaces 实现接口
 * h InvocationHandler
 *
 * 动态代理实现思路
 * 实现功能: 通过Proxy的 newProxyInstance返回代理对象
 * 1. 声明一段源码 (动态产生代理)
 * 2. 编译源码(JDK Compiler API)，产生新的类(代理类)
 * 3. 将这个类load到内存中，产生一个新的对象 (代理对象)
 * 4. return 代理对象
 *
 * @createTime 2021年06月01日 10:37:00
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        Car car = new Car();  // 被代理类 实现了Moveable接口
        InvocationHandler h = new TimeHandler(car); // 执行者 增强Moveable接口下方法的功能
        // 代理类
        Moveable m = (Moveable) Proxy.newProxyInstance(car.getClass().getClassLoader(), car.getClass().getInterfaces(), h);
        m.move();
        //System.out.println(m.toString()); 对 toString() equals() hashCode() 方法也会进行handler.invoke()中的代理逻辑处理
    }
}
