package zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Ticket12306.java
 * @Description TODO
 * @createTime 2021年11月11日 13:47:00
 */
public class Ticket12306 implements Runnable{
    private int tickets = 10;

    private InterProcessMutex lock;

    public Ticket12306() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);

        //client = CuratorFrameworkFactory.newClient("47.110.154.185:2181", 60 * 1000, 15 * 1000, retryPolicy);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("47.110.154.185:2181")
                .sessionTimeoutMs(60*1000)
                .connectionTimeoutMs(15* 1000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        lock = new InterProcessMutex(client,"/lock");
    }

    @Override
    public void run() {
        while (true){

            try {
                // 获取锁
                lock.acquire(3, TimeUnit.SECONDS);
                if(tickets > 0){
                    System.out.println(Thread.currentThread() + ":" + tickets);
                    tickets--;
                }
            } catch (Exception e) {
                System.out.println(Thread.currentThread() + "没拿到锁！");
            }finally {
                // 释放锁
                try {
                    lock.release();
                } catch (Exception e) {
                    System.out.println(Thread.currentThread() + "释放锁失败！");
                }
            }

        }
    }
}
