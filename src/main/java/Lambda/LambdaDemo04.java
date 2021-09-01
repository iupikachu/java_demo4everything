package Lambda;


import java.util.function.UnaryOperator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaDemo03.java
 * @Description TODO
 * @createTime 2021年05月20日 19:58:00
 */

class User2{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String say(UnaryOperator<String> sayHello){
        return sayHello.apply(this.username);
    }
}

public class LambdaDemo04 {
    public static void main(String[] args) {
        User2 user = new User2();
        user.setUsername("cqp");
        UnaryOperator func = (s) -> "hello" + s;
        String say = user.say(func);
        System.out.println(say);
    }

}
