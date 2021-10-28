package pattern.singleton.lazy.demo3;

import java.io.Serializable;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Singleton.java
 * @Description 懒汉式3: 静态内部类加载
 *              JVM 加载外部类时，是不会加载静态内部类的，只有静态内部类的属性|方法被调用时才会被加载。
 *              加载静态内部类的时候，由于类加载器加载类的方法底层使用了 synchronized ，所以对于多线程页没有问题，并且严格保证实例化顺序
 *              既保证了多线程下的安全，又保证了 Singleton 的唯一性。
 *
 * @createTime 2021年10月16日 15:33:00
 */
public class Singleton implements Serializable {

    //解决反射破坏单例模式: 由于静态内部类实现时，没有实例变量，所以声明一个 bool类型的变量说明对象是否已经创建，并且声明为 static 让所有锁、线程共享
    private static boolean flag =false;

    private Singleton(){

        synchronized (Singleton.class){
            // 判断 flag 是否是 true，如果是 true，说明非第一次访问，对象已经创建，抛异常
            if(flag){
                throw new RuntimeException("不能创建多个对象");
            }

            // 将flag设置为true
            flag = true;
        }
    }

    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    //解决序列化破坏单例模式: 当进行反序列化时，会自动调用该方法，如果没有定义该方法，则返回新 new 出来的对象
    public Object readResolve(){
        return SingletonHolder.INSTANCE;
    }

}
