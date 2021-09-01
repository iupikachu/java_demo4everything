package spring.IOC;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Main.java
 * @Description TODO
 * @createTime 2021年06月02日 16:22:00
 */
public class Main {
    public static void main(String[] args) {
      ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       User user1 = ((User) ctx.getBean("user"));
       User user2 = ctx.getBean("user", User.class);
       User user3 = ctx.getBean(User.class);
       System.out.println("user1 = " + user1);
       System.out.println("user2 = " + user2);
       System.out.println("user3 = " + user3);
    }
}
