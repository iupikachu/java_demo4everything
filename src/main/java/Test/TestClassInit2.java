package Test;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestClassInit2.java
 * @Description TODO
 * @createTime 2022年03月21日 19:28:00
 */
public class TestClassInit2 {

    public static void main(String[] args) {
        System.out.println("a:" + fatherClass.a);
        System.out.println("b:" + fatherClass.b);
    }

   static class fatherClass{
       static int b = 2;
       static {
           System.out.println("fatherClass init!");
           a = 3;
           b = 4;
       }
       static int a = 1;
   }


}

