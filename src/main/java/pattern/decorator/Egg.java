package pattern.decorator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Egg.java
 * @Description 鸡蛋类 (具体的装饰者角色)
 * @createTime 2021年10月19日 20:41:00
 */
public class Egg extends Garnish{


    public Egg(FastFood fastFood ) {
        super(fastFood, 1, "鸡蛋");
    }

    @Override
    public float cost() {
        // 计算价格
        return getPrice() + getFastFood().cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }
}
