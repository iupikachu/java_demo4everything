package pattern.singleton.lazy.demo1;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2021年10月16日 15:01:00
 */
public class Test {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance1 == instance2);
    }
}
