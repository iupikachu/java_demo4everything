package Concurrency.heima;

import java.util.concurrent.locks.LockSupport;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName test5.java
 * @Description TODO
 * @createTime 2021年12月23日 10:07:00
 */
public class test5 {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        ParkUnpark pu = new ParkUnpark(5);

        t1 = new Thread(() ->{
            pu.print("a",t2);
        });

        t2 = new Thread(() ->{
            pu.print("b",t3);
        });

        t3 = new Thread(() ->{
            pu.print("c",t1);
        });

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);

    }
}

class ParkUnpark{

    public void print(String str, Thread next){
        for (int i = 0; i < loopNum; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }

    private int loopNum;

    public ParkUnpark(int loopNum) {
        this.loopNum = loopNum;
    }
}