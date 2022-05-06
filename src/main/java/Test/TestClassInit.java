package Test;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestClassInit.java
 * @Description TODO
 * @createTime 2022年03月17日 20:00:00
 */
public class TestClassInit {
    public static void main(String[] args) {
        System.out.println(SubClass.Sub);
        //SubClass subClass = new SubClass();

    }
}

class SSClass{
    static {
        System.out.println("static: SSClass init");
    }

    public static int SS = 1;

    public SSClass(){
        System.out.println("generator: SSClass init");
    }
}

class SuperClass extends SSClass{
    static {
        System.out.println("static: SuperClass init!");
    }

    public static int Super = 2;

    public SuperClass(){
        System.out.println("generator: SuperClass init!");
    }
}

class SubClass extends SuperClass{


    static {
        System.out.println("static: SubClass init");
        int Sub = 4;
    }
    public static int Sub = 3;


    public SubClass(){
        System.out.println("generator: SubClass init!");
    }


}

