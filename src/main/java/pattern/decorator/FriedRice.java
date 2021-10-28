package pattern.decorator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName FriedRice.java
 * @Description 炒饭(具体构件角色)
 * @createTime 2021年10月19日 20:32:00
 */
public class FriedRice extends FastFood{

    public FriedRice(){
        super(10,"炒饭");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
