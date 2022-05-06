package Concurrency.heima;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Test7.java
 * @Description 两阶段终止 volatile
 * @createTime 2021年12月24日 20:34:00
 */
public class Test7 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination1 tpt = new TwoPhaseTermination1();
        tpt.start();
        Thread.sleep(8000);
        tpt.stop();
    }
}

@Slf4j
class TwoPhaseTermination1{
    private Thread monitor;
    private volatile boolean stop = false;
    public void start(){
        monitor = new Thread(() ->{
            while(true){
                if(stop){
                    log.debug("处理后事。。。");
                    break;
                }
                try {
                    Thread.sleep(2000);
                    log.debug("监控 cpu 使用率");
                } catch (InterruptedException e) {
                }
            }
        });
        monitor.start();
    }

    public void stop(){
        stop = true;
        monitor.interrupt(); // 如果 monitor 线程睡眠时间过长，执行 interrupt 打断其睡眠
    }
}
