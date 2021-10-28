package debug;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName debug.java
 * @Description dubug练习 并发修改异常
 * @createTime 2021年10月09日 20:11:00
 */
public class debug {
    private static void operaterList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("cqp");
        list.add("iu");
        list.add("sadas");
        list.add("asddfsffaf");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String name = iterator.next();
            if(name.equals("sadas")){
                // 改变集合元素个数
                list.add("hhh");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("begin");
        operaterList();
        System.out.println("end");
    }
}

