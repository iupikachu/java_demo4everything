package juc.future;


import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.*;

/**
 * @Auther: cqp
 * @Date: 2023/8/31 15:45
 * @Description:
 */

@Slf4j
public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        UserInfoService userInfoService = new UserInfoService();
        MedalInfoService medalInfoService = new MedalInfoService();

        long startTime = System.currentTimeMillis();

        // 调用用户服务
        FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(new Callable<UserInfo>() {

            @Override
            public UserInfo call() throws Exception {

                return userInfoService.getUserInfo();
            }
        });
        executorService.submit(userInfoFutureTask);

        // 模拟主线程其他操作
        Thread.sleep(300);

        FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(new Callable<MedalInfo>() {
            @Override
            public MedalInfo call() throws Exception {
                return medalInfoService.getMedal();
            }
        });
        executorService.submit(medalInfoFutureTask);

        // 获取结果
        UserInfo userInfo = userInfoFutureTask.get();// 获取个人信息   阻塞调用
        MedalInfo medalInfo = medalInfoFutureTask.get(); // 获取金牌信息
        log.info("个人信息{}", userInfo);
        log.info("金牌信息{}", medalInfo);
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
