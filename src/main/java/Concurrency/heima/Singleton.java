package Concurrency.heima;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Singleton.java
 * @Description Balking 犹豫模式 实现线程的单例
 * @createTime 2021年12月24日 21:52:00
 */
public final class Singleton implements Serializable {

    private Singleton(){}

    private static class LazyHolder{
        static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return LazyHolder.INSTANCE;
    }

}

