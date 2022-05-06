package Concurrency.heima;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName 保证 t2 在 t1 前先运行  (park & notify)
 * @Description TODO
 * @createTime 2021年12月22日 16:00:00
 */

@Slf4j
public class test2 {
    public static void main(String[] args) {
        Thread t1 =  new Thread(() ->{
            LockSupport.park();
            log.debug("1");
            },"t1");
        t1.start();

        Thread t2 = new Thread(() ->{
            log.debug("2");
            LockSupport.unpark(t1);
        },"t2");
        t2.start();
    }
}
