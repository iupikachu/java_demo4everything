package pattern.decorator;

import Test.main;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Client.java
 * @Description TODO
 * @createTime 2021年10月19日 20:50:00
 */
public class Client {
    public static void main(String[] args) {
        // 点一份炒饭
        FastFood food = new FriedRice();

        System.out.println(food.getDesc() + " " + food.cost() + "元");

        System.out.println("====炒饭加蛋====");
        food = new Egg(food);
        System.out.println(food.getDesc() + " " + food.cost() + "元");

        System.out.println("====炒饭加蛋再加蛋====");
        food = new Egg(food);
        System.out.println(food.getDesc() + " " + food.cost() + "元");


        System.out.println("====炒饭加蛋再加蛋加培根====");
        food = new Bacon(food);
        System.out.println(food.getDesc() + " " + food.cost() + "元");

    }
}
