package netty.BIOTest.ThreadPoll;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName ClientHandler.java
 * @Description TODO
 * @createTime 2021年10月15日 09:16:00
 */
public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private final RequestHandler requestHandler;

    public ClientHandler(Socket clientSocket, RequestHandler requestHandler) {
        this.clientSocket = clientSocket;
        this.requestHandler = requestHandler;
    }

    @Override
    public void run() {
        try(Scanner input = new Scanner(clientSocket.getInputStream())){
            // 对于每个socket 数据进行交互
            while (true){
                String request = input.nextLine();
                if(request.equals("quit")){
                    break;
                }
                System.out.println(String.format("From %s : %s",clientSocket.getRemoteSocketAddress(),request));
                String response = requestHandler.handle(request);
                clientSocket.getOutputStream().write(response.getBytes());
            }
        }catch(NoSuchElementException | IOException e){
            System.out.println("客户端异常中断");
            //e.printStackTrace();
        }
    }
}
