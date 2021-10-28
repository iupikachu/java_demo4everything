package Concurrency.kuangshen.Thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestLock.java
 * @Description TODO
 * @createTime 2021年09月24日 20:00:00
 */
public class TestLock {
    public static void main(String[] args) {
        buyTicket1 buyTicket = new buyTicket1();

        new Thread(buyTicket,"线程1").start();
        new Thread(buyTicket,"线程2").start();
        new Thread(buyTicket,"线程3").start();

    }
}



class buyTicket1 implements Runnable{

    int ticketNums = 1000;

    // 定义 lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                lock.lock();
                if(ticketNums > 0){

                    System.out.println(Thread.currentThread().getName()+"拿到了:"+ticketNums--);
                }else {
                    break;
                }
            } finally {
                lock.unlock(); // 解锁
            }
        }

    }
}
