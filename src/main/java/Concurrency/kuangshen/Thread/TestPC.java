package Concurrency.kuangshen.Thread;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestPC.java
 * @Description 测试生产者——消费者模型    ---》 管程法
 * @createTime 2021年09月25日 13:44:00
 */


// 生产者 消费者 产品 缓冲区
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();
    }
}

// 生产者
class Productor extends Thread{
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    // 生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            container.push(new Chicken(i));
            System.out.println("生产了"+i+"只鸡");
        }
    }
}

// 消费者
class Consumer extends  Thread{
    SynContainer container;

    public Consumer(SynContainer container){
        this.container = container;
    }

    
    // 消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了-->第"+container.pop().id+"只鸡");
        }
    }
}

// 产品
class Chicken{
    int id;  // 产品编号

    public Chicken(int id) {
        this.id = id;
    }
    
    
}

// 缓冲区
class SynContainer{
    // 容器大小
    Chicken[] chickens = new Chicken[10];
    // 容器计数器
    int count = 0;

    // 生产者放入产品
    public synchronized void push(Chicken chicken){
        // 如果容器满了，就需要等待消费者
        while (count == 10){
            // 通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果没满，丢入产品
        chickens[count] = chicken;
        count++;
        this.notifyAll();
        // 通知消费者进行消费
    }


    // 消费者消费产品
    public synchronized Chicken pop(){
        // 判断能否消费
        while (count == 0){
            // 等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果可以消费
        count--;
        Chicken chicken = chickens[count];
        this.notifyAll();
        // 吃完了，通知生产者生产
        return chicken;
    }
}
