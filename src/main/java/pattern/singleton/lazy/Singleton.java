package pattern.singleton.lazy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Singleton.java
 * @Description 静态内部类实现单例模式
 * @createTime 2022年04月19日 14:15:00
 */
public class Singleton {

    private Singleton(){}

    private static class SingletonHolder{
        private static Singleton Instance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.Instance;
    }


    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
