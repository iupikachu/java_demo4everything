package Proxy;

import Proxy.JDKProxy.Moveable;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName CarLogProxy.java
 * @Description 静态代理
 *  一个代理只能为一个接口服务，程序开发中必然产生许多的代理类
 *  代理的类在编译时就确定了，只能代理一种类型
 * @createTime 2021年06月01日 09:52:00
 */
public class CarLogProxy implements Moveable {
    private Moveable m;

    public CarLogProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("汽车日志开启");
        m.move();
        System.out.println("汽车日志结束");
    }
}
