package Concurrency.demo;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName SynchronizedDemo.java
 * @Description TODO
 * @createTime 2021年10月11日 21:24:00
 */
public class SynchronizedDemo {
    private final Object lock = new Object();

    private static  int money = 0;

    // 非静态方法
    private synchronized void add(){
        money++;
    }

    // 静态方法
    private static synchronized  void delete(){
        money--;
    }

    public void codeBlock(){
        // 代码块
        synchronized (lock){
            money = money*2;
        }
    }

}
