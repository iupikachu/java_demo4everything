package pattern.singleton.lazy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Outer.java
 * @Description 延迟加载
 * @createTime 2022年04月19日 14:29:00
 */
public class Outer {
    static {
        System.out.println("Outer init");
    }

    private Outer(){
        System.out.println("Outer instance create");
    }

    private static class Inner{
        static {
            System.out.println("Inner init");
        }

        private Inner(){
            System.out.println("Inner instance create");
        }

        private static Outer outer = new Outer();
    }

    public static void main(String[] args) {
        System.out.println("outer main execute");

    }
}
