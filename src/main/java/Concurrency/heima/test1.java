package Concurrency.heima;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName test1.java
 * @Description 保证 t2 在 t1 前先运行  (wait & notify)
 * @createTime 2021年12月22日 15:42:00
 */
@Slf4j
public class test1 {
    static final Object lock = new Object();
    static boolean t2Runned = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                while(!t2Runned){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");
            }
        },"t1");

        Thread t2 = new Thread(() ->{
            synchronized (lock){
                log.debug("2");
                t2Runned = true;
                lock.notify();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
