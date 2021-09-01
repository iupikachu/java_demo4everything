package Proxy;

import Proxy.JDKProxy.Moveable;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Car3.java
 * @Description 聚合
 * @createTime 2021年06月01日 09:48:00
 */
public class Car3 implements Moveable {
    private Car car;

    public Car3(Car car) {
        this.car = car;
    }


    @Override
    public void move() {
        long starttime = System.currentTimeMillis();
        System.out.println("汽车开始行驶");
        car.move();
        long endtime = System.currentTimeMillis();
        System.out.println("汽车结束行驶,行驶了:"+(endtime-starttime)+"时间");
    }
}
