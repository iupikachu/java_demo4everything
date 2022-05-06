package Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName LambdaTest2.java
 * @Description TODO
 * @createTime 2021年12月29日 20:26:00
 */
public class LambdaTest2 {
    @Test
    public void test1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("花了"+ aDouble + "去网吧打游戏");
            }
        });

        happyTime(100, a -> {
            System.out.println("去洗脚花了" + a);
        });
    }

    public  void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void test3(){
        List<String> list = Arrays.asList("天天","天太","一按下","天下","方法","学长");
        List<String> res = filterString(list, (s) -> {
            return s.contains("天");
        });
        System.out.println(res);
    }

    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for (String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }



}
