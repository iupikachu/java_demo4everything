package netty.heima.c3;


import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;


/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestEventLoop.java
 * @Description TODO
 * @createTime 2021年10月28日 14:48:00
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup(2); // io 事件 普通任务 定时任务
        // EventLoopGroup group = new DefaultEventLoop();  // 普通任务 定时任务
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        // 执行普通任务
        group.next().submit(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                }
        );


    }
}
