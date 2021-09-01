package netty;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName IOClient.java
 * @Description TODO
 * @createTime 2021年05月19日 14:01:00
 */
public class IOClient {
    public static void main(String[] args) {

        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}
