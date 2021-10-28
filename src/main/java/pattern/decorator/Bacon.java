package pattern.decorator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Bacon.java
 * @Description 培根类 (具体的装饰者角色)
 * @createTime 2021年10月19日 20:46:00
 */
public class Bacon extends Garnish {


    public Bacon(FastFood fastFood) {
        super(fastFood, 2, "培根");
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }

    @Override
    public float cost() {
        return super.getPrice() + getFastFood().cost();
    }

}
