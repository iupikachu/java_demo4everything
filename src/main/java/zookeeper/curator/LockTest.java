package zookeeper.curator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LockTest.java
 * @Description zookeeper 实现分布式锁 模拟12306抢票
 * @createTime 2021年11月11日 13:46:00
 */
public class LockTest {
    public static void main(String[] args) {

        Ticket12306 ticket12306 = new Ticket12306();

        Thread t1 = new Thread(ticket12306,"携程");
        Thread t2 = new Thread(ticket12306,"飞猪");

       t1.start();
       t2.start();
    }
}
