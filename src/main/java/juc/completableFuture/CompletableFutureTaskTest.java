package juc.completableFuture;

import juc.future.MedalInfo;
import juc.future.MedalInfoService;
import juc.future.UserInfo;
import juc.future.UserInfoService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: cqp
 * @Date: 2023/8/31 16:18
 * @Description:
 */

@Slf4j
public class CompletableFutureTaskTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UserInfoService userInfoService = new UserInfoService();
        MedalInfoService medalInfoService = new MedalInfoService();
        long startTime = System.currentTimeMillis();

        CompletableFuture<UserInfo> userInfoCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return userInfoService.getUserInfo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 模拟主线程其余操作
        Thread.sleep(300);
        CompletableFuture<MedalInfo> medalInfoCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return medalInfoService.getMedal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        UserInfo userInfo = userInfoCompletableFuture.get();
        MedalInfo medalInfo = medalInfoCompletableFuture.get();
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
