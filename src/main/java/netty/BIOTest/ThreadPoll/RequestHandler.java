package netty.BIOTest.ThreadPoll;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName RequestHandler.java
 * @Description TODO
 * @createTime 2021年10月15日 09:15:00
 */
public class RequestHandler {
    public String handle(String request){
        return  "FROM SERVER ：HELLO " + request + ".\n";
    }
}
