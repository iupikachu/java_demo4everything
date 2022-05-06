package Concurrency.heima;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName test6.java
 * @Description 两阶段终止
 * @createTime 2021年12月23日 22:00:00
 */

@Slf4j
public class test6 {
    public static void main(String[] args) throws InterruptedException {
        TwoPahseTermination tpt = new TwoPahseTermination();
        tpt.start();

        Thread.sleep(8000);
        tpt.stop();
    }
}

@Slf4j
class TwoPahseTermination{
    private Thread monitor; // 监控线程

    // 启动监控线程
    public void start(){
        monitor = new Thread(() ->{
            while(true){
                if(monitor.isInterrupted()){
                    log.debug("处理后事。。。");
                    break;
                }
                try {
                    Thread.sleep(2000);  // 打断情况1
                    log.debug("监控cpu使用率"); //  打断情况2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 重新设置打断标记
                    monitor.interrupt();
                }
            }
        });

        monitor.start();
    }

    // 停止监控线程
    public void stop(){
        monitor.interrupt();
    }
}
