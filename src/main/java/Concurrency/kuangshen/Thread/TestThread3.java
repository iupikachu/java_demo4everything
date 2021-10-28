package Concurrency.kuangshen.Thread;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestThread.java
 * @Description TODO
 * @createTime 2021年09月22日 19:11:00
 */
public class TestThread3 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("线程执行: 我在看代码!!!!!!!!"+i);
        }
    }

    public static void main(String[] args) {
        // 创建 runnable 接口的实现类
        TestThread3 testThread3 = new TestThread3();

        // 创建线程对象，通过线程对象来开启我们的线程，代理模式  testThread3 相当于 target
        Thread thread = new Thread(testThread3);
        thread.start();


        for (int i = 0; i < 1000; i++) {
            System.out.println("主线程执行: 我在学习多线程"+i);
        }
    }
}
