package Concurrency.kuangshen.lambda;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestLambda1.java
 * @Description
 *
 * 推导 Lambda 表达式
 *
 * @createTime 2021年09月22日 20:43:00
 */
public class TestLambda1 {

     //3. 静态内部类
    static class Like2 implements ILike{
         @Override
         public void lambda() {
             System.out.println("i like lambda by 静态内部类");
         }
     }

    public static void main(String[] args) {
        // 实现类
        ILike like;
         like = new Like();
         like.lambda();

         // 静态内部类
         like = new Like2();
         like.lambda();

         // 4. 局部内部类
         class Like3 implements ILike{
             @Override
             public void lambda() {
                 System.out.println("i like lambda by 局部内部类");
             }
         }

         like = new Like3();
         like.lambda();

         // 匿名内部类 没有类的名称,必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda by 匿名内部类 ");
            }
        };
        like.lambda();


        // 6. 用 lambda 简化
        like = () -> {
            System.out.println("i like lambda by lambda");
        };
        like.lambda();
    }




}

// 1. 定义一个函数式接口
interface ILike{
    void lambda();
}

// 2. 实现类
class Like implements ILike{

    @Override
    public void lambda() {
        System.out.println("i like lambda");
    }
}