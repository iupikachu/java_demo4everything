package netty.BIOTest;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName BIOServer.java
 * @Description Bio 同步阻塞IO
 * @createTime 2021年10月14日 21:42:00
 */
public class BIOServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BIOServer has started,listening on port:"+serverSocket.getLocalSocketAddress());
            // 接收许多的socket
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection from " + clientSocket.getRemoteSocketAddress());
                try(Scanner input = new Scanner(clientSocket.getInputStream())){
                    // 对于每个socket 数据进行交互
                    while (true){
                        String request = input.nextLine();
                        if(request.equals("quit")){
                            break;
                        }
                        System.out.println(String.format("From %s : %s",clientSocket.getRemoteSocketAddress(),request));
                        String response = "FROM BIOServer Hello " + request + "\n";
                        clientSocket.getOutputStream().write(response.getBytes());
                    }
                }catch(NoSuchElementException e){
                    System.out.println("客户端异常中断");
                    //e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
