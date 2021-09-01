package Proxy.CglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName CglibProxy.java
 * @Description TODO
 * @createTime 2021年06月01日 11:12:00
 */
public class CglibProxy implements MethodInterceptor {
     // 创建代理类
     private Enhancer enhancer = new Enhancer();

     public Object getProxy(Class clazz){
         // 设置创建子类(代理类)的类
         enhancer.setSuperclass(clazz);
         enhancer.setCallback(this);
         return enhancer.create();
     }

    /**
     *  拦截所有目标类方法的调用
     * @param obj     增强的对象
     * @param method  被拦截的方法
     * @param args    被拦截方法的参数
     * @param proxy   代理   触发父类的方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {


        System.out.println("日志开始......");
        // 代理类调用父类(需要增强的对象)方法
        proxy.invokeSuper(obj, args);
        System.out.println("日志结束......");
        return null;
    }
}
