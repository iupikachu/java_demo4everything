package juc.future;

/**
 * @Auther: cqp
 * @Date: 2023/8/31 15:39
 * @Description:
 */
public class UserInfoService {
    public UserInfo getUserInfo() throws InterruptedException {
        Thread.sleep(300);//模拟调用耗时
        return new UserInfo("666", "草长莺飞陈平安", 27); //一般是查数据库，或者远程调用返回的
    }

}
