package Concurrency.demo;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestYield.java
 * @Description
 *
 * 与sleep类似，也是Thread类提供的一个静态的方法，它也可以让当前正在执行的线程暂停，让出CPU资源给其他的线程。
 * 但是和sleep()方法不同的是，它不会进入到阻塞状态，而是进入到就绪状态。
 *
 * @createTime 2021年09月26日 09:21:00
 */
public class TestYield {
    public static void main(String[] args) {
        new MyThread("低级", 1).start();
        new MyThread("中级", 5).start();
        new MyThread("高级", 10).start();
    }
}

class MyThread extends Thread {
    public MyThread(String name, int pro) {
        super(name);// 设置线程的名称
        this.setPriority(pro);// 设置优先级
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(this.getName() + "线程第" + i + "次执行！");
            if (i % 5 == 0){
                System.out.println(this.getName() + "线程 Yield()");
                Thread.yield();
            }

        }
    }
}
