package Concurrency.kuangshen.Thread;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName UnsafeBuyTicket.java
 * @Description 线程同步不安全案例
 * @createTime 2021年09月23日 22:01:00
 */

// 不安全的买票
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket,"买票1号").start();
        new Thread(buyTicket,"买票2号").start();
        new Thread(buyTicket,"黄牛").start();
    }
}

class BuyTicket implements Runnable{

    // 票
    private int ticketNums = 100;
    boolean flag = true;  // 外部停止方式

    @Override
    public void run() {
        // 买票
        while(flag){
            try {
                Thread.sleep(100);
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //synchronized  同步方法 拿到这个方法的对象的锁
    private synchronized void buy() throws InterruptedException {
        // 判断是否有票
        if(ticketNums <= 0){
            flag = false;
            return;
        }
        // 模拟延时

        Thread.sleep(100);

        // 买票
        System.out.println(Thread.currentThread().getName()+"拿到"+ticketNums--);
    }
}