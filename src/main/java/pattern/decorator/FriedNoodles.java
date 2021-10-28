package pattern.decorator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName FriedNoodles.java
 * @Description 炒面 (具体构件角色)
 * @createTime 2021年10月19日 20:35:00
 */
public class FriedNoodles extends FastFood{
    public FriedNoodles(){
        super(12,"炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
