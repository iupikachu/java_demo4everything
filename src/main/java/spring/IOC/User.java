package spring.IOC;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年06月02日 16:20:00
 */
public class User {
    private String username;
    private String address;
    private Integer id;

    public User() {
        System.out.println("。。。。。。init");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
