package pattern.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName ProxyFactory.java
 * @Description 获取代理对象的工厂类
 *              代理类也实现了对应的接口
 * @createTime 2021年10月18日 20:21:00
 */
public class ProxyFactory {

    // 声明目标对象
   private TrainStation trainStation = new TrainStation();

   // 获取代理对象的方法
   public SellTickets getProxyObject(){

       /*
       ClassLoader loader,      类加载器，用于加载代理类，通过目标对象获得类加载器
       Class<?>[] interfaces,   代理类实现的接口的字节码对象
       InvocationHandler h      代理对象的调用处理程序
        */
     SellTickets proxyObject = (SellTickets)Proxy.newProxyInstance(
             trainStation.getClass().getClassLoader(),
             trainStation.getClass().getInterfaces(),
             new InvocationHandler() {
                 @Override
                 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                     /**
                      *  Object proxy : 代理对象    和proxyObject 是同一个对象，在 invoke 方法中基本不用
                      *  Method method : 对接口中的方法进行封装
                      *  Object[] args: 调用方法的实际参数
                      *  返回值就是代理方法的返回值
                      */
                     System.out.println("方法增强");
                     Object obj = method.invoke(trainStation, args); // 这里返回的是代理对象调用方法的返回值

                     return obj;
                 }
             }
     );
     return proxyObject;
   }
}
