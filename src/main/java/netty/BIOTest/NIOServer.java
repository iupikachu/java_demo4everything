package netty.BIOTest;

import netty.BIOTest.ThreadPoll.RequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName NIOServer.java
 * @Description TODO
 * @createTime 2021年10月15日 10:22:00
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false); // 设置为非阻塞
        serverChannel.bind(new InetSocketAddress(9999));
        System.out.println("NIO SERSERVER has started,listening on port:"+serverChannel.getLocalAddress());

        Selector selector = Selector.open();
        // 每个客户端连接上之后，就把客户端注册到 selector 选择器上,默认状态是Accepted
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        RequestHandler requestHandler = new RequestHandler();  // 这是自己封装的

        // 轮询，服务端不断轮询，等待客户端连接
        while (true){
            int select = selector.select();
            if(select == 0){
                continue;
            }

            // 如果selector 上有客户端连接上来的话，取出对应的 channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 判断 selectionKey 中的 channel 状态如何
                if(key.isAcceptable()){
                  ServerSocketChannel server_Channel = (ServerSocketChannel) key.channel(); // 服务端channel
                  SocketChannel client_Channel = server_Channel.accept(); // 通过服务端的 channel 拿到客户端的 channel

                    // 打印客户端channel的来源
                    System.out.println("Connection from (clientChannel) : " + client_Channel.getRemoteAddress());
                    client_Channel.configureBlocking(false);

                    // 将客户端 channel 的状态改为 read
                    client_Channel.register(selector,SelectionKey.OP_READ);
                }

                // 接下来轮询，发现状态是 readable
                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();

                    // 数据的交互以 buffer 为中间桥梁
                    channel.read(buffer);
                    String request = new String(buffer.array()).trim();
                    buffer.clear();
                    System.out.println(String.format("FROM %s : %s",channel.getRemoteAddress(),request));
                    String response = requestHandler.handle(request);
                    channel.write(ByteBuffer.wrap(response.getBytes()));
                }
                iterator.remove();
            }
        }

    }
}
