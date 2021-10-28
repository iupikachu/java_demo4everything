package Concurrency.kuangshen.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestPool.java
 * @Description TODO
 * @createTime 2021年09月25日 14:39:00
 */
public class TestPool {

    public static void main(String[] args) {
        // 创建服务，创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 执行
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        // 关闭连接
        service.shutdownNow();
    }
}
class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}