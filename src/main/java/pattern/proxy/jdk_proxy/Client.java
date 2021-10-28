package pattern.proxy.jdk_proxy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Client.java
 * @Description TODO
 * @createTime 2021年10月18日 20:39:00
 */
public class Client {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        SellTickets proxyObject = factory.getProxyObject();
        proxyObject.sell();

    }
}
