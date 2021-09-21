package Lambda.sgg;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaTest2.java
 * @Description  java 内置的四大核心函数式接口
 *
 *  消费型接口 Consumer<T>       void accept(T t)
 *  供给型接口 Supplier<T>       T get()
 *  函数型接口 Function<T,R>     R apply(T t)
 *  断定型接口 Predicate<T>      boolean test(T t)
 *
 * @createTime 2021年09月21日 15:29:00
 */
public class LambdaTest2 {



    @Test
    public void test1(){
        // 匿名内部类
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("买苹果消费了:" + aDouble);
            }
        });

        // Lambda
        happyTime(666, money -> System.out.println("买香蕉消费了:" + money));
    }
    public void happyTime(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }





    @Test
    public void test2(){

        List<String> list = Arrays.asList("妙蛙种子","妙蛙草","妙蛙花","皮卡丘","蚊香蝌蚪","呱呱泡蛙","蚊香蛙","快泳蛙","利欧路");

        // 匿名内部类
        List<String> list1 = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("蛙");
            }
        });
        System.out.println(list1);
        System.out.println("*******************");

        // Lambda
        List<String> list2 = filterString(list, s -> s.contains("蛙"));
        System.out.println(list2);
    }

    // 根据给定的规则，过滤集合中的字符串，此规则由 Predict 的方法决定
    public List<String> filterString(List<String> list, Predicate<String> predicate){
        List<String> filterList = new ArrayList<>();
        for(String s : list){
            if(predicate.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }


    //  Function 中的 R apply(T t)
    //  Math 的 Long round(Double d)
    //  参数列表和返回值一样，可以使用方法引用
    @Test
    public void test3(){
        // 匿名内部类
        Function<Double,Long> function = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(function.apply(12.4));
        System.out.println("************");

        // Lambda
        Function<Double,Long> function1 = d -> Math.round(d);
        System.out.println(function1.apply(12.5));
        System.out.println("************");

        // 方法引用
        Function<Double,Long> function2 = Math::round;
        System.out.println(function2.apply(12.222));
    }

    @Test
    public void test4(){

    }
}
