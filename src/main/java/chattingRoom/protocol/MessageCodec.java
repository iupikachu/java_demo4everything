package chattingRoom.protocol;

import chattingRoom.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName MessageCodec.java
 * @Description 消息编解码器
 * @createTime 2021年11月01日 19:41:00
 */

@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {

    @Override
    public void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        // 1.     4字节的魔数
        out.writeBytes(new byte[]{1,2,3,4});
        // 2.     1字节版本号
        out.writeByte(1);
        // 3.     1字节的序列化方式  jdk 0   json 1
        out.writeByte(0);
        //4.      1字节的指令类型
        out.writeByte(msg.getMessageType());
        //5.      4字节序列号用于双工通信 提供异步能力
        out.writeInt(msg.getSequenceId());
        // 无意义  1字节 扩展位 对齐16的填充
        out.writeByte(0xff);
        //6. 获取内容的字节数
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        //7.     4字节长度
        out.writeInt(bytes.length);
        //8. 写入内容
        out.writeBytes(bytes);

    }

    @Override
        public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serializertype = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();

        in.readByte(); // 读取 1 个 byte 的扩展位
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes,0,length);

        // 反序列化为对象
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message =(Message) ois.readObject();
        log.debug("{},{},{},{},{},{},",magicNum,version,serializertype,messageType,sequenceId,length);
        log.debug("{}",message);

        out.add(message); // 供下一个 handler 调用


    }
}
