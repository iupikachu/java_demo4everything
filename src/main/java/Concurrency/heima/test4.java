package Concurrency.heima;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName test4.java
 * @Description
 * @createTime 2021年12月23日 09:48:00
 */
public class test4 {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
           awaitSignal.print("a",a,b);
        },"t1").start();

        new Thread(() -> {
            awaitSignal.print("b",b,c);
        },"t2").start();

        new Thread(() -> {
            awaitSignal.print("c",c,a);
        },"t3").start();

        Thread.sleep(1000);
        awaitSignal.lock();
        try{
            System.out.println("开始了。。。");
            a.signal();
        }finally {
            awaitSignal.unlock();
        }
    }
}

class AwaitSignal extends ReentrantLock{
    private int loopNum;

    public AwaitSignal(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String str, Condition current, Condition next){
        for (int i = 0; i < loopNum; i++) {
            lock();
            try{
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}