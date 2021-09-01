package Lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaDemo06.java
 * @Description TODO
 * @createTime 2021年05月20日 20:26:00
 */
public class LambdaDemo06 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "里斯", "张五");
        names.stream().forEach(s -> System.out.println(s));

    }
}
