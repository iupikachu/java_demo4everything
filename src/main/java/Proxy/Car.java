package Proxy;

import Proxy.JDKProxy.Fixable;
import Proxy.JDKProxy.Moveable;

import java.util.Random;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Car.java
 * @Description TODO
 * @createTime 2021年06月01日 09:12:00
 */
public class Car implements Moveable, Fixable {
    @Override
    public void move() {
        // 开车
        try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("汽车行驶中");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fix() {
        // 修车
        try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("汽车维修中");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
