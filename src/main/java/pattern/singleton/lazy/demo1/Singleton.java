package pattern.singleton.lazy.demo1;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Singleton.java
 * @Description 懒汉式1-（线程不安全: 无 synchronized     线程安全: 加上 synchronized）
 *              缺点: synchronized 太重了，而且大部分操作都是读操作，不涉及对象的创建
 * @createTime 2021年10月16日 14:55:00
 */
public class Singleton {
    private Singleton(){}

    private static Singleton instance;

    public  static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
