package Lambda;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaDemo01.java
 * @Description TODO
 * @createTime 2021年05月20日 16:58:00
 */
public class LambdaDemo01 {
    public static void main(String[] args) {
        ICalculator ic = i -> i*i;
        int square = ic.square(5);
        System.out.println("square =" + square);
    }

    public static interface ICalculator {
        int square(int i);
    }
}
