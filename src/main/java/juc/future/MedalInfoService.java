package juc.future;

/**
 * @Auther: cqp
 * @Date: 2023/8/31 15:42
 * @Description:
 */
public class MedalInfoService {
    public MedalInfo getMedal() throws InterruptedException {
        Thread.sleep(500);
        return new MedalInfo("121", "奥运金牌");
    }

}
