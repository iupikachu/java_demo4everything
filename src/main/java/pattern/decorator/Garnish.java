package pattern.decorator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Garnish.java
 * @Description 装饰者类 (抽象装饰角色)
 * @createTime 2021年10月19日 20:37:00
 */
public abstract class Garnish extends FastFood{

    // 声明快餐类的变量
    private FastFood fastFood;


    public Garnish( FastFood fastFood, float price, String desc) {
        super(price, desc);
        this.fastFood = fastFood;
    }

    public FastFood getFastFood() {
        return fastFood;
    }

    public void setFastFood(FastFood fastFood) {
        this.fastFood = fastFood;
    }
}
