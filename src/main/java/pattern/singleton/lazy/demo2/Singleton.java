package pattern.singleton.lazy.demo2;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Singleton.java
 * @Description 懒汉式2: 双重检查锁
 *              第一次检查实例有无创建
 *              拿锁
 *              拿到锁之后再检查实例有无创建
 *              创建单例对象
 *
 *              存在问题: 由于 jvm 在实例化对象的时候会进行 优化 指令重排序 会导致空指针异常
 *              解决方法: volatile 保证 可见性 和 有序性
 *
 *              添加 volatile 关键字之后的双重检查锁模式是一种比较好的单例实现模式，能够保证在多线程 的情况下线程安全也不会有性能问题
 *
 * @createTime 2021年10月16日 15:19:00
 */
public class Singleton {
    private Singleton(){

        // 反射破解单例模式需要添加的代码
        if(instance != null){
            throw new RuntimeException();
        }

    }

    private static volatile Singleton instance;

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                // 抢到锁之后再次检查对象有无创建
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
