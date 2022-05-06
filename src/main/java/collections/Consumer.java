package collections;

import java.util.Random;
import java.util.Vector;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Consumer.java
 * @Description 消费者
 * @createTime 2022年05月04日 15:10:00
 */
public class Consumer implements Runnable {

    // 公共资源
    private final Vector shareQueue;

    public Consumer(Vector shareQueue) {
        this.shareQueue = shareQueue;
    }

    @Override
    public void run() {
        Random r = new Random();

        System.out.println("start consumer id = " +Thread.currentThread().getId());

        try {
            while (true){
                // 模拟延迟
                Thread.sleep(r.nextInt(1000));

                // 当队列空时阻塞等待
                while (shareQueue.isEmpty()){
                    synchronized (shareQueue){
                        System.out.println("Queue is empty, consumer:" + Thread.currentThread().getId() + "is waiting, size : " + shareQueue.size());
                        shareQueue.wait();
                    }
                }

                // 队列不空时持续消费数据
                synchronized (shareQueue){
                    System.out.println("consumer consume data :" + shareQueue.remove(0) + ", size :" + shareQueue.size());
                    shareQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
