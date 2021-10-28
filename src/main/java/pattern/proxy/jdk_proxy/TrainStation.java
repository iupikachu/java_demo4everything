package pattern.proxy.jdk_proxy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TrainStation.java
 * @Description 火车站
 * @createTime 2021年10月18日 20:20:00
 */
public class TrainStation implements SellTickets,ChangeTickets {
    @Override
    public void sell() {
        System.out.println("火车站卖票");
    }

    @Override
    public int ChangeTickets(int userId) {

        System.out.println("你好，" + userId);
        System.out.println("还有:10 张票");
        return 10;
    }
}
