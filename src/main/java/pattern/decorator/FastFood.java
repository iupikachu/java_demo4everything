package pattern.decorator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName FastFood.java
 * @Description 快餐类 (抽象构件角色)
 * @createTime 2021年10月19日 20:27:00
 */
public abstract class FastFood {

    private float price; // 价格
    private String desc; // 描述

    public FastFood() {

    }

    public FastFood(float price, String desc) {
        this.price = price;
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public abstract float cost();
}
