package Concurrency.demo;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Demo.java
 * @Description 使用Thread类
 * @createTime 2021年09月21日 13:23:00
 */
public class Demo {

    //继承thread类 重写run方法
    public static  class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {

        /*
         * 我们在程序⾥⾯调⽤了start() ⽅法后，虚拟机会先为我们创建⼀个线程，然 后等到这个线程第⼀次得到时间⽚时再调⽤run()⽅法。
         *  注意不可多次调⽤start() ⽅法。在第⼀次调⽤start() ⽅法后，再次调⽤start()  ⽅法会抛出异常 IllegalThreadStateException。
         */
        Thread myThread = new MyThread();
        myThread.start();

       // Thread 继承了 Runnable 接口, Runnable接口是函数式接口，所以可以使用 Lambda
       new Thread(() ->{
           System.out.println("java 8 匿名内部类 Lambda");
       }).start();

    }
}
