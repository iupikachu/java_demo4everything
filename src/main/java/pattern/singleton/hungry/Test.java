package pattern.singleton.hungry;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2021年10月16日 14:46:00
 */
public class Test {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance1 == instance2);

        SingletonOfEnum instance3 = SingletonOfEnum.INSTANCE;
        SingletonOfEnum instance4 = SingletonOfEnum.INSTANCE;

        System.out.println(instance3 == instance4);
    }
}
