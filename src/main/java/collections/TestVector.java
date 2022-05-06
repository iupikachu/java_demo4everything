package collections;

import Test.main;

import java.util.Vector;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName TestVector.java
 * @Description 测试 Vector
 * @createTime 2022年05月04日 14:04:00
 */
public class TestVector {

    public static void main(String[] args) {
        Vector vector = new Vector();
        vector.add(1);
        vector.add("s");
        vector.add(2);
        vector.add("c");
        getAll(vector);

        vector.remove(0);
        getAll(vector);

        vector.add("end");
        getAll(vector);
    }

    public static void getAll(Vector vector){
        System.out.println("*******");
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i));
        }
    }

}
