package Proxy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Car2.java
 * @Description 继承
 * @createTime 2021年06月01日 09:42:00
 */
public class Car2 extends Car{
    @Override
    public void move() {
        long starttime = System.currentTimeMillis();
        System.out.println("汽车开始行驶");
        super.move();
        long endtime = System.currentTimeMillis();
        System.out.println("汽车结束行驶,行驶了:"+(endtime-starttime)+"时间");
    }
}
