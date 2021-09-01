package Lambda;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaDemo03.java
 * @Description TODO
 * @createTime 2021年05月20日 19:58:00
 */

//@FunctionalInterface
interface SayHello{
    String sayHello(String name);
}

class User{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String say(SayHello sayHello){
        return sayHello.sayHello(this.username);
    }
}

public class LambdaDemo03 {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("cqp");
        String say = user.say((s) -> "hello " + s);
        System.out.println(say);
    }

}
