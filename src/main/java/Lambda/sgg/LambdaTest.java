package Lambda.sgg;

import org.junit.Test;

import java.util.Comparator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaTest.java
 * @Description 尚硅谷教程
 *              Lambda 表达式的使用举例
 * @createTime 2021年09月21日 13:46:00
 */
public class LambdaTest {
    @Test
    public void test1(){

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1 run起来了");
            }
        };
        r1.run();
        System.out.println("*********************");


        Runnable r2 = () -> System.out.println("r2 run起来了");
        r2.run();

    }

    @Test
    public void test2(){

        Comparator<Integer> comparateInteger = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int result = comparateInteger.compare(12,21);
        System.out.println(result);
        System.out.println("*******************");


        // Lambda 表达式的写法
        Comparator<Integer> comparateInteger2 = ((o1, o2) -> Integer.compare(o1,o2));

        int result2 = comparateInteger2.compare(32, 21);
        System.out.println(result2);
        System.out.println("*******************");


        // 方法引用的写法
        Comparator<Integer>comparateInteger3 = Integer :: compare;

        int result3 = comparateInteger3.compare(12, 12);
        System.out.println(result3);

    }


}
