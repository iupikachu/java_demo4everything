package Lambda.sgg;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaTest1.java
 * @Description
 *
 * Lambda 表达式的使用
 *
 * 1. 举例  (o1,o2) -> Integer.compare(o1,o2);
 * 2. 格式:
 *         -> : lambda操作符 或 箭头操作符
 *         ->左边 : lambda 形参列表  (其实就是接口中的抽象方法的形参列表)
 *         ->右边 : lambda 体  (其实就是重写的抽象方法的方法体)
 *
 * 3. Lambda 表达式的使用 (6种情况)
 * 4. Lambda 表达式的本质: 作为函数式接口的实例  如果一个接口中只声明了一个抽象方法，则此接口就称为函数式接口
 * 5. 使用 @FunctionalInterface 注解，来检验一个接口是不是函数式接口
 * 6. 以前用匿名内部实现类表示的现在都可以用 Lambda 表达式来写
 *
 *
 *
 *
 * @createTime 2021年09月21日 14:23:00
 */
public class LambdaTest1 {
    // 语法格式一： 无参，无返回值
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

    // 语法格式二: 需要一个参数，但是没有返回值
    @Test
    public void test2(){

        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("消费者消费了一个苹果");
        System.out.println("******************");

        Consumer<String> consumer =(String s) -> {
            System.out.println(s);
        };
        consumer.accept("消费者消费了一个香蕉");

    }

    // 语法格式三: 数据类型可以省略，因为可以由编译器依据泛型推断得出，"类型推断"
    @Test
    public void test3(){
        Consumer<String> con =(s) -> {
            System.out.println(s);
        };
        con.accept("消费者消费了一个苹果3");
        System.out.println("******************");
    }

    // 语法格式四: Lambda 若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test4(){
        Consumer<String> con =s -> {
            System.out.println(s);
        };
        con.accept("消费者消费了一个苹果4");
        System.out.println("******************");
    }

    // 语法格式五: Lambda 需要两个或两个以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test5(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("o1:"+o1);
                System.out.println("o2:"+o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com.compare(11, 12));
        System.out.println("*******************");

        Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println("o1:"+o1);
            System.out.println("o2:"+o2);
            return o1.compareTo(o2);
        };
        System.out.println(comparator.compare(12, 11));
    }

    // 语法格式六: 当lambda只有一条语句，rerturn 与 {} 都可以省略
    @Test
    public void test6(){
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator.compare(12, 11));
    }

}
