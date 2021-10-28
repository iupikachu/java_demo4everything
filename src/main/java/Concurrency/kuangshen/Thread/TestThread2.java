package Concurrency.kuangshen.Thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestThread2.java
 * @Description  练习使用 继承Thread 实现多线程下载图片
 * @createTime 2021年09月22日 18:50:00
 */
public class TestThread2 extends Thread{
    private String url;         // 网络图片地址
    private String file_name;   // 保存文件名

    public TestThread2(String url, String file_name) {
        this.url = url;
        this.file_name = file_name;
    }

    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoad(url,file_name);
        System.out.println("下载了图片:"+file_name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("http://s1.dgtle.com/dgtle_img/ins/2021/03/27fc67c202103271209437687_1800_500.jpeg","p1.jpeq");
        TestThread2 t2 = new TestThread2("http://s1.dgtle.com/dgtle_img/ins/2021/03/27c159d20210327120525123_1800_500.jpeg","p2.jpeq");
        TestThread2 t3 = new TestThread2("http://s1.dgtle.com/dgtle_img/ins/2021/03/271711a202103271209436802_1800_500.jpeg","p3.jpeq");

        t1.start();
        t2.start();
        t3.start();
    }

    // 下载器
    class WebDownLoader{
        // 下载
        public void downLoad(String url, String file_name){
            try {
                FileUtils.copyURLToFile(new URL(url),new File(file_name));
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("IO异常，downLoad方法报错");
            }
        }
    }
}



