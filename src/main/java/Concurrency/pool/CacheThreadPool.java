package Concurrency.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName CacheThreadPool.java
 * @Description 缓存线程池
 *
 * CachedThreadPool 会将创建的线程缓存起来，线程60s内空闲就会被销毁，只要有请求到来就必须要找到一条工作线程去处理
 * 如果当前没有空闲线程，就创建一条新的线程
 *
 * @createTime 2022年03月11日 12:57:00
 */
@Slf4j
public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i < 10000; i++) {
            executorService.submit(new task());
            log.debug("第"+ i + "条任务");
        }
    }
}

class task implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
