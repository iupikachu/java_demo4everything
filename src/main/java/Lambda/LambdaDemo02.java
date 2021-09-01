package Lambda;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaDemo02.java
 * @Description TODO
 * @createTime 2021年05月20日 18:16:00
 */
public class LambdaDemo02 {
    interface MathOperation{
        int operation(int a,int b);

    }

    interface GreetingService{
        void sayMessage(String message);
    }

    public static void main(String[] args) {
        // 声明参数类型
        MathOperation addition = (int a,int b) -> a + b;

        // 不需要声明参数类型
        MathOperation subtraction = (x,y) -> x - y;

        // 大括号中的返回语句
        MathOperation multiplication = (int a,int b) -> {return a*b;};

        // 没有大括号及返回语句
        MathOperation division = (int a,int b) -> a/b;

        System.out.println("10 + 5 ="+addition.operation(10,5));
        System.out.println("10 - 5 ="+subtraction.operation(10,5));
        System.out.println("10 * 5 ="+multiplication.operation(10,5));
        System.out.println("10 / 5 ="+division.operation(10,5));

        GreetingService greetingService1 = message -> System.out.println("Hello"+message);
        GreetingService greetingService2 = (message) -> System.out.println("Hello"+message);

        greetingService1.sayMessage("World");
        greetingService2.sayMessage("World!");

    }



}

