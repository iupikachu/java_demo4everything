package Proxy;

import Proxy.JDKProxy.Moveable;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName CarTimeProxy.java
 * @Description TODO
 * @createTime 2021年06月01日 09:54:00
 */
public class CarTimeProxy implements Moveable {

    private Moveable m;

    public CarTimeProxy(Moveable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long starttime = System.currentTimeMillis();
        System.out.println("汽车开始行驶");
        m.move();
        long endtime = System.currentTimeMillis();
        System.out.println("汽车结束行驶,行驶了:"+(endtime-starttime)+"毫秒！");
    }
}
