package Concurrency.demo;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestJoin.java
 * @Description TODO
 * @createTime 2021年09月26日 09:27:00
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 t=new MyThread1();
        t.start();
        t.join();
        System.out.println(Thread.currentThread().getName()+"运行");

    }

    }


class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + "执行！");
        }
    }
}

