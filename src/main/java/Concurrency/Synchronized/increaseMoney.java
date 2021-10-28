package Concurrency.Synchronized;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName increaseMoney.java
 * @Description 二个线程，A线程让队伍经济 +1 ，B线程让经济 + 2，分别执行一千次，预期的结果应该是3000
 * @createTime 2021年10月08日 18:56:00
 */
public class increaseMoney {
    private static int money = 0;
    public static void increaseMoney(int n){
        money += n;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increaseMoney(1);
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increaseMoney(2);
            }
        });

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println("当前总的钱:" + money);
    }
}
