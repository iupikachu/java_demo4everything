package Proxy.CglibProxy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName cglibProxyTest.java
 * @Description TODO
 * @createTime 2021年06月01日 13:37:00
 */
public class cglibProxyTest {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Train t  = (Train) proxy.getProxy(Train.class);
//        t.move();
//        t.aaa();
        System.out.println(t.toString());
    }
}
