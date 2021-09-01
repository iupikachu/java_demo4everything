package Proxy.JDKProxy_simulation;

import org.apache.commons.io.FileUtils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
/**
 * @author cqp
 * @version 1.0.0
 * @ClassName Proxy.java
 * @Description
 *
 *  * 动态代理实现思路
 *  * 实现功能: 通过Proxy的 newProxyInstance返回代理对象
 *  * 1. 声明一段源码 (动态产生代理)
 *  * 2. 编译源码(JDK Compiler API)，产生新的类(代理类)
 *  * 3. 将这个类load到内存中，产生一个新的对象 (代理对象)
 *  * 4. return 代理对象
 *
 * @createTime 2021年06月01日 13:55:00
 */

public class Proxy {

    public static Object newProxyInstance() throws Exception {
        String str =
                "package Proxy;\n" +
                        "\n" +
                        "import Proxy.JDKProxy.Moveable;\n" +
                        "\n" +
                        "/**\n" +
                        " * @author cqp\n" +
                        " * @version 1.0.0\n" +
                        " * @ClassName CarTimeProxy.java\n" +
                        " * @Description TODO\n" +
                        " * @createTime 2021年06月01日 09:54:00\n" +
                        " */\n" +
                        "public class CarTimeProxy implements Moveable {\n" +
                        "\n" +
                        "    private Moveable m;\n" +
                        "\n" +
                        "    public CarTimeProxy(Moveable m) {\n" +
                        "        this.m = m;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void move() {\n" +
                        "        long starttime = System.currentTimeMillis();\n" +
                        "        System.out.println(\"汽车开始行驶\");\n" +
                        "        m.move();\n" +
                        "        long endtime = System.currentTimeMillis();\n" +
                        "        System.out.println(\"汽车结束行驶,行驶了:\"+(endtime-starttime)+\"毫秒！\");\n" +
                        "    }\n" +
                        "}";

        String filename = System.getProperty("user.dir")+"/src/main/proxy/$Proxy.java";
        File file = new File(filename);
        FileUtils.writeStringToFile(file,str);
        return null;
    }
}
