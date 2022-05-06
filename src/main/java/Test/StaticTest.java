package Test;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName StaticTest.java
 * @Description TODO
 * @createTime 2022年03月21日 19:46:00
 */
public class StaticTest {


    static {
        i = 2;
    }
    static int i = 1;
    public static void main(String[] args) {
        System.out.println(StaticTest.i);
    }
}
