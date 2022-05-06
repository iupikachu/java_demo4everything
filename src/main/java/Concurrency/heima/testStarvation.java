package Concurrency.heima;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName testStarvation.java
 * @Description TODO
 * @createTime 2022年02月21日 20:07:00
 */

@Slf4j
public class testStarvation {
    static final List<String> MENU = Arrays.asList("地三鲜","宫保鸡丁","辣子鸡丁","烤鸡翅");
    static Random RANDOM = new Random();
    static String cooking(){
        return MENU.get(RANDOM.nextInt(MENU.size()));
    }

    public static void main(String[] args) {
        ExecutorService waitPool = Executors.newFixedThreadPool(1);
        ExecutorService cookPool = Executors.newFixedThreadPool(1);

        waitPool.execute(() ->{
            log.debug("处理点菜...");
            Future<String> f = cookPool.submit(() -> {
                log.debug("做菜");
                return cooking();
            });
            try{
                log.debug("上菜:{}",f.get());
            } catch (ExecutionException  | InterruptedException e) {
                e.printStackTrace();
            }
        });

        waitPool.execute(() ->{
            log.debug("处理点菜...");
            Future<String> f = cookPool.submit(() -> {
                log.debug("做菜");
                return cooking();
            });
            try{
                log.debug("上菜:{}",f.get());
            } catch (ExecutionException  | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
