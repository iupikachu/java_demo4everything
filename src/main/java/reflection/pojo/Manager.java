package reflection.pojo;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Manager.java
 * @Description TODO
 * @createTime 2021年11月06日 16:44:00
 */
public class Manager implements People{
    @Override
    public void show() {
        System.out.println("我是老板");
    }
}
