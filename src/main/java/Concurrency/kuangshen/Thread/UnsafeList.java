package Concurrency.kuangshen.Thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName UnsafeList.java
 * @Description 线程不安全的集合
 * @createTime 2021年09月24日 14:01:00
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread( ()->{
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
