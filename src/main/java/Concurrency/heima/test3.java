package Concurrency.heima;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName test3.java
 * @Description 线程1输出 a 5次， 线程2输出 b 5次， 线程3输出 c 5 次，要求输出 abcabcabcabcabc
 * @createTime 2021年12月22日 16:19:00
 */
public class test3 {
    public static void main(String[] args) {
        waitNotify waitNotify = new waitNotify(5, 1);

        new Thread(() ->{
           waitNotify.print("a",1);
        },"t1").start();

        new Thread(() ->{
            waitNotify.print("b",2);
        },"t2").start();

        new Thread(() ->{
            waitNotify.print("c",3);
        },"t3").start();
    }
}

class waitNotify{

    /**
     * 输出 flag
     *  a   1
     *  b   2
     *  c   3
     */

    private int loopNum;
    private int flag;

    public waitNotify(int loopNum, int flag) {
        this.loopNum = loopNum;
        this.flag = flag;
    }
    
    public void print(String str,int curflag){
        for (int i = 0; i < loopNum; i++) {
            synchronized (this){
              while (curflag != flag){
                  try {
                      this.wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
                System.out.print(str);
                flag = curflag % 3 + 1;
                this.notifyAll();
            }

        }
    }
}