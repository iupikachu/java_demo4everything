import java.lang.reflect.Proxy;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName PlayTest.java
 * @Description TODO
 * @createTime 2022年03月21日 21:11:00
 */
public class PlayTest {
    public static void main(String[] args) {
        zhanan zhanan = new zhanan();
        WherePlayHandler wherePlayHandler = new WherePlayHandler(zhanan);
        Playable o = (Playable) Proxy.newProxyInstance(zhanan.getClass().getClassLoader(), zhanan.getClass().getInterfaces(), wherePlayHandler);
        o.play();
    }
}
