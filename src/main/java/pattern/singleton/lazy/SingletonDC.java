package pattern.singleton.lazy;




/**
 * @author cqp
 * @version 1.0.0
 * @ClassName SingletonDC.java
 * @Description 双重检查锁
 * @createTime 2022年04月19日 20:09:00
 */
public class SingletonDC {
    private static volatile SingletonDC Instance = null;

    private SingletonDC(){}

    public SingletonDC getInstance(){

        if(Instance == null){
            synchronized(this){
                if(Instance == null){
                    Instance = new SingletonDC();
                }
            }
        }
        return Instance;
    }
}
