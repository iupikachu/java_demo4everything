package netty.heima.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestNettyPromise.java
 * @Description TODO
 * @createTime 2021年10月29日 10:12:00
 */
@Slf4j
public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 准备Eventloop 对象
        EventLoop eventLoop = new NioEventLoopGroup().next();
        // 2.主动创建 promise 结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        new Thread(() -> {
            log.debug("开始计算");
            try {
                int i = 1 / 0;
                Thread.sleep(1000);
                promise.setSuccess(70);
            } catch (Exception e) {
                e.printStackTrace();
                promise.setFailure(e);
            }

        }).start();

        // 4.接收结果的线程
        log.debug("等待结果...");
        log.debug("结果是：{}",promise.get());
    }

}
