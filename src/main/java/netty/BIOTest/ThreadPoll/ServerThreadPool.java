package netty.BIOTest.ThreadPoll;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName ServerThreadPool.java
 * @Description TODO
 * @createTime 2021年10月15日 09:21:00
 */
public class ServerThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        RequestHandler requestHandler = new RequestHandler();
        try(ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BIOServer has started,listening on port:"+serverSocket.getLocalSocketAddress());
            while (true){
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ClientHandler(clientSocket,requestHandler));
                System.out.println("Connection from " + clientSocket.getRemoteSocketAddress());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
