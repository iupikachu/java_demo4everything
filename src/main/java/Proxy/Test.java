package Proxy;

import Proxy.Car;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2021年06月01日 09:14:00
 */
public class Test {
    public static void main(String[] args) {
        Car car = new Car();
//        car.move();

//        Car2 car2 = new Car2();  继承
//        car2.move();

//        Car3 car3 = new Car3(car);   聚合
//        car3.move();
//        CarLogProxy carLogProxy = new CarLogProxy(car);
//        CarTimeProxy carTimeProxy = new CarTimeProxy(carLogProxy);

        // 静态代理
        CarTimeProxy carTimeProxy = new CarTimeProxy(car);
        CarLogProxy carLogProxy = new CarLogProxy(carTimeProxy);
        carLogProxy.move();
    }
}
