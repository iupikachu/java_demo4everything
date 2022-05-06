package Concurrency.heima;

import Test.main;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName testThreadPoolExecutors.java
 * @Description 测试固定线程池
 * @createTime 2022年01月18日 16:36:00
 */

@Slf4j
public class testThreadPoolExecutors {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(() ->{
            log.debug("1");
        });

        pool.execute(() ->{
            log.debug("2");
        });

        pool.execute(() ->{
            log.debug("3");
        });
    }
}
