package Concurrency.heima;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestCountDownLatch.java
 * @Description TODO
 * @createTime 2022年03月02日 09:47:00
 */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        Random r = new Random();
        String[] all = new String[10];

        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            service.submit(() ->{
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    all[finalJ] = i + "%";   // lambda 表达式不能使用改变的量，需要在外面重新赋值
                    System.out.print("\r" + Arrays.toString(all));
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("\n游戏开始");
        service.shutdown();
    }
}
