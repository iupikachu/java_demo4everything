package Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaDemo05.java
 * @Description TODO
 * @createTime 2021年05月20日 20:18:00
 */
public class LambdaDemo05 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "里斯", "张五");
        List<String> list = names.stream().filter(s -> s.startsWith("张")).collect(Collectors.toList());
        for (String s : list){
            System.out.println("s ="+s);
        }
    }
}
