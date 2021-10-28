package pattern.singleton.hungry;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Singleton.java
 * @Description 单例模式: 饿汉式：类加载时就直接创建单例对象
 *                       缺点：单例对象创建之后，但是很少使用，那么内存浪费了
 *
 *                       由于加载类的时候，就创建了单例对象，加载类底层使用了 synchronized，所以饿汉式不存在多线程的问题
 *                       但是懒汉式只有在使用的时候，才去创造单例： 就有两种操作：创建单例对象 和 访问单例对象
 *                       多线程下就存在问题了，创建单例对象只能由第一个调用的线程创建。
 *                       而且创建单例对象只需要一次（需要同步操作），而访问单例对象有很多此（不需要同步操作），
 *                       所以才有了 懒汉的 二重锁检查: 先检查有无创建对象，无创建，再去拿锁创建对象，不然直接读
 *                                内部静态锁：利用锁加载机制，天生就避免了多线程的并发问题
 *                     
 * @createTime 2021年10月16日 14:42:00
 */
public class Singleton {

    // 私有构造方法，保证外界无法构造对象
    private Singleton(){}

    // 随着类加载，静态单例对象创建
    private static Singleton instance = new Singleton();

    // 提供一个公共方法，让外界获取这个类加载时创建的对象
    public static Singleton getInstance(){
        return instance;
    }
}
