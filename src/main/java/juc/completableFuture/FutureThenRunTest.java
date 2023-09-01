package juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: cqp
 * @Date: 2023/8/31 17:12
 * @Description:
 */
public class FutureThenRunTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> firstFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("先执行第一个CompletableFuture方法");
            return 1212;
        });

        CompletableFuture<Void> thenRunFuture = firstFuture.thenRun(() -> {
            System.out.println("接着执行第二个任务");
            return;
        });
        System.out.println(thenRunFuture.get());
    }
}
