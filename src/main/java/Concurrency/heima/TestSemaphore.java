package Concurrency.heima;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestSemaphore.java
 * @Description 测试信号量
 * @createTime 2022年03月01日 15:43:00
 */

@Slf4j
public class TestSemaphore {
    public static void main(String[] args) {
        // 1. 创建 semaphore
        Semaphore semaphore = new Semaphore(3);

        //2. 10 个线程同时运行
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try{
                    log.debug("running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("end...");
                }finally {
                    semaphore.release();
                }

            }).start();
        }
    }
}
