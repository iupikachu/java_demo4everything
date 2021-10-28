package Concurrency.Synchronized;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName increaseMoneyLock.java
 * @Description TODO
 * @createTime 2021年10月08日 19:01:00
 */
public class increaseMoneyLock {
    private static int money = 0;
    private static final Object monitor = new Object();
    public static void increaseMoney(int n){
        money += n;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            synchronized (monitor){
                for (int i = 0; i < 1000; i++) {
                    increaseMoney(1);
                }
            }

        });

        Thread b = new Thread(() -> {
           synchronized (monitor){
               for (int i = 0; i < 1000; i++) {
                   increaseMoney(2);
               }
           }
        });

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println("当前总的钱:" + money);
    }
}
