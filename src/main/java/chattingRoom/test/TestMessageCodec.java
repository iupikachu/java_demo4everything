package chattingRoom.test;

import chattingRoom.message.LoginRequestMessage;
import chattingRoom.protocol.MessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestMessageCodec.java
 * @Description TODO
 * @createTime 2021年11月01日 20:37:00
 */
public class TestMessageCodec {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                new LengthFieldBasedFrameDecoder(1024,12,4,0,0 ), // 解决粘包半包问题
                new MessageCodec());
        // 测试 encode 方法
        LoginRequestMessage message = new LoginRequestMessage("zhangsan","123");
        channel.writeOutbound(message);

        // 测试 decode 方法
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null,message,buf); // 准备数据 加密,将messgae 加密后放入buf中
        // 入站
       // channel.writeInbound(buf);

        // 测试半包现象

        ByteBuf s1 = buf.slice(0, 100);
        ByteBuf s2 = buf.slice(100,buf.readableBytes() - 100);
        s1.retain(); // 引用计数+1
        channel.writeInbound(s1); // 写入bytebuff 会调用一次release方法，把引用计数减去1
        channel.writeInbound(s2);


    }
}
