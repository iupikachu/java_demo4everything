package netty.heima.c3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName EventLoopClient.java
 * @Description TODO
 * @createTime 2021年10月28日 20:54:00
 */
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {

        ChannelFuture channelFuture= new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                // 1. 连接到服务器
                .connect(new InetSocketAddress("localhost", 8080));

                channelFuture.sync();
                Channel channel = channelFuture.channel();
        // 2. 向服务器发送数据
                channel.writeAndFlush("hello, world!");
        System.out.println(channel);
        System.out.println("");
    }
}
