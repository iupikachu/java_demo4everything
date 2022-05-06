import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName WherePlayHandler.java
 * @Description TODO
 * @createTime 2022年03月21日 21:08:00
 */
public class WherePlayHandler implements InvocationHandler {
    Object target;

    public WherePlayHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //增强开始
        System.out.println("去 helens 喝酒");
        method.invoke(target);
        System.out.println("Helens 喝完了，去 commune");
        return null;
    }
}
