package Concurrency.kuangshen.Thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestCallable.java
 * @Description 线程创建方式3 实现callable接口
 * @createTime 2021年09月22日 19:59:00
 */
public class TestCallable implements Callable<Boolean> {
    private String url;         // 网络图片地址
    private String file_name;   // 保存文件名

    public TestCallable(String url, String file_name) {
        this.url = url;
        this.file_name = file_name;
    }

    @Override
    public Boolean call() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoad(url,file_name);
        System.out.println("下载了图片:"+file_name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("http://s1.dgtle.com/dgtle_img/ins/2021/03/27fc67c202103271209437687_1800_500.jpeg","p1.jpeq");
        TestCallable t2 = new TestCallable("http://s1.dgtle.com/dgtle_img/ins/2021/03/27c159d20210327120525123_1800_500.jpeg","p2.jpeq");
        TestCallable t3 = new TestCallable("http://s1.dgtle.com/dgtle_img/ins/2021/03/271711a202103271209436802_1800_500.jpeg","p3.jpeq");

        // 1.创建执行服务  手动创建线程池
        ExecutorService service = Executors.newFixedThreadPool(3);

        // 2.提交执行
        Future<Boolean> r1 = service.submit(t1);
        Future<Boolean> r2 = service.submit(t2);
        Future<Boolean> r3 = service.submit(t3);

        // 3.获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        // 4.关闭服务
        service.shutdownNow();
    }



    // 下载器
    class WebDownLoader{
        // 下载
        public void downLoad(String url, String file_name) {
            try {
                FileUtils.copyURLToFile(new URL(url), new File(file_name));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO异常，downLoad方法报错");
            }
        }
    }
}



