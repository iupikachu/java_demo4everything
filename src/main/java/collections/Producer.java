package collections;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Producer.java
 * @Description 生产者
 * @createTime 2022年05月04日 14:10:00
 */
public class Producer implements Runnable{

    private volatile boolean isRunning = true;

    // 阻塞队列存放公共资源，使用 vector 保证并发安全
    // 疑问❓：反正都用了 synchronized 锁了，就算用线程不安全的也没事吧
    private final Vector shareQueue;

    // 公共资源的最大数目
    private final int SIZE;

    // 生产资源计数，static 修饰， 所有的消费者都用一个计数值
    // 疑问❓：反正都是拿到了锁进行+1，感觉普通的 int 也可以
    private static AtomicInteger count = new AtomicInteger();

    public Producer(Vector shareQueue, int SIZE) {
        this.shareQueue = shareQueue;
        this.SIZE = SIZE;
    }

    @Override
    public void run() {
        Random r = new Random();
        int data;

        System.out.println("start producer id = " + Thread.currentThread().getId());
        try {
            while (isRunning){
                // 模拟延迟
                Thread.sleep(r.nextInt(1000));

                // 当队列满时阻塞等待
                while (shareQueue.size() == SIZE){
                    // 都去抢锁，抢到锁的 wait() 阻塞等待，让出锁，唤醒没抢到锁的线程
                    // 抢不到锁的线程，在这个锁的等待队列上阻塞等待唤醒
                    synchronized (shareQueue){
                        System.out.println("Queue is full, producer " + Thread.currentThread().getId() + "is waiting, size :" + shareQueue.size());
                        shareQueue.wait();
                    }
                }

                // 队列不满时持续创造新元素
                synchronized (shareQueue){
                    // 生产数据
                    data = count.incrementAndGet();
                    shareQueue.add(data);

                    System.out.println("producer create data :" + data + ", size:" + shareQueue.size());
                    shareQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        isRunning = false;
    }
}
