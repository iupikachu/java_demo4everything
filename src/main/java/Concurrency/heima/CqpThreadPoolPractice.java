package Concurrency.heima;

import com.sun.jmx.remote.internal.ArrayQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName CqpThreadPoolPractice.java
 * @Description 手写线程池
 * @createTime 2022年01月16日 10:27:00
 */

@Slf4j
public class CqpThreadPoolPractice {



    public static void main(String[] args) {
       CqpThreadPool cqpThreadPool = new CqpThreadPool(1,1000,TimeUnit.MILLISECONDS,1,
               (queue,task) ->{
                    queue.offer(task,1500,TimeUnit.MILLISECONDS);
               });

        for (int i = 0; i < 3; i++) {
            int j = i;
            cqpThreadPool.execute(() ->{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}",j);
            });
        }
    }

}


@FunctionalInterface
interface CqpRejectPolicy<T>{
    void reject(CqpBlockingQueue queue, T task);
}

@Slf4j
class CqpThreadPool{
    // 任务队列
    private CqpBlockingQueue<Runnable> taskQueue;

    // 线程集合
    private HashSet<Worker> workers = new HashSet<Worker>();

    // 核心数
    private int coreSize;

    // 获取任务的超时时间
    private long timeOut;

    // 时间单位
    private TimeUnit timeUnit;

    // 拒绝策略
    private CqpRejectPolicy cqpRejectPolicy;

    public CqpThreadPool( int coreSize, long timeOut, TimeUnit timeUnit,int queueCapcity, CqpRejectPolicy cqpRejectPolicy) {
       this.taskQueue = new CqpBlockingQueue<>(queueCapcity);
        this.coreSize = coreSize;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
        this.cqpRejectPolicy = cqpRejectPolicy;
    }

    // 把任务传递给线程池进行执行
    // 1) 正在执行的线程数没有超过核心数，直接新建线程对象执行任务
    // 2) 正在执行的线程数超过了核心数，将任务放入任务队列
    public void execute(Runnable task){
        synchronized (workers){
            if(workers.size() < coreSize){
                Worker worker = new Worker(task);
                log.debug("线程池新增线程执行任务:" + worker);
                workers.add(worker);
                worker.start();
            }else {
                // 执行拒绝策略
                //taskQueue.put(task);
                taskQueue.tryPut(cqpRejectPolicy,task);
            }
        }
    }


class Worker extends Thread{
    private Runnable task;

    public Worker(Runnable task){
        this.task = task;
    }

    @Override
    public void run() {
        // 执行任务
        // 1) 当 task 不为空，执行任务
        // 2) 当 task 执行完毕，再接着从任务队列获取任务并执行
        while (task != null || (task = taskQueue.poll(timeOut,timeUnit)) != null){
            try {
                log.debug("正在执行任务:" + task);
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                task = null;
            }
        }

        synchronized (workers){
            log.debug("worker 被移除", this);
            workers.remove(this);
        }
    }
}
}





@Slf4j
class CqpBlockingQueue<T>{
    private Deque<T> queue = new ArrayDeque<>();

    private int capcity;

    private ReentrantLock lock = new ReentrantLock();

    // 生产者条件变量
    private Condition FullWaitSet = lock.newCondition();

    // 消费者条件变量
    private Condition EmptyWaitSet = lock.newCondition();

    public CqpBlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    // 阻塞获取队列中的任务
    public T take(){
        lock.lock();
        try{
            while(queue.isEmpty()){
                try {
                    EmptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            FullWaitSet.signal();
            log.debug("take 阻塞获取任务:" + t);
            return t;
        }finally {
            lock.unlock();
        }
    }

    // 超时阻塞获取队列中的任务
    public T poll(long timeOut, TimeUnit timeUnit){
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeOut);
            while (queue.isEmpty()){
                try {
                    if(nanos <= 0){
                        return null;
                    }
                    nanos = EmptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            log.debug("poll 超时阻塞获取任务:" + t);
            FullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    // 阻塞添加任务
    public void put(T task){
        lock.lock();
        try{
            while (queue.size() == capcity){
                try {
                    log.debug("put 阻塞等待添加任务:" + task);
                    FullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            log.debug("put 阻塞添加任务:" + task);
            EmptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }

    // 超时阻塞添加任务
    public boolean offer(T task, long timeOut, TimeUnit timeUnit){
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeOut);
            while (queue.size() == capcity){
                log.debug("offer 超时阻塞等待添加任务：" + task);
                try {
                    if(nanos <= 0){
                        return false;
                    }
                    nanos = FullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            log.debug("offer 阻塞添加任务:" + task);
            EmptyWaitSet.signal();
            return true;
        }finally {
            lock.unlock();
        }
    }

    public int size(){
        lock.lock();
        try{
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    public void tryPut(CqpRejectPolicy cqpRejectPolicy, T task){
        lock.lock();
        try{
            // 判断队列是否满了
            if(queue.size() == capcity){
                cqpRejectPolicy.reject(this,task);
            }else{
                // 队列未满
                log.debug("加入任务队列{}", task);
                queue.addLast(task);
                EmptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }

}
