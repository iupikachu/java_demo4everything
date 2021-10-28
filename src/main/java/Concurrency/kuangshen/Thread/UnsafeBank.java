package Concurrency.kuangshen.Thread;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName UnsafeBank.java
 * @Description 不安全取钱
 * @createTime 2021年09月24日 13:40:00
 */
public class UnsafeBank {
    public static void main(String[] args) {
        // 账户
        Account account = new Account(1000,"结婚基金");

        Drawing you = new Drawing(account,50,"你");
        Drawing gf= new Drawing(account,100,"wife");

        you.start();
        gf.start();
    }


}

// 账户
class  Account{
     int money; // 余额
     String name; // 卡号

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行: 模拟取款
class  Drawing extends Thread{
    Account account;
    int drawingMoney; // 取的钱
    int nowMoney;  // 手里的钱

    public Drawing(Account account,int drawingMoney,String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
        this.nowMoney= nowMoney;
    }

    @Override
    public void run() {

        // 锁的对象就是变化的量 共同操作的资源
        synchronized (account){
            // 判断有没有钱
            if ((account.money - drawingMoney) < 0) {
                System.out.println(Thread.currentThread().getName()+"钱不够，取不了");
                return;
            }

            // 模拟延时，让 gf 也可以进入取钱环节
            // sleep 可以放大问题的发生性 进行模拟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 卡内余额 = 余额 - 取的钱
            account.money = account.money - drawingMoney;

            // 手里的钱
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name+ "余额为:" +account.money);
            System.out.println(Thread.currentThread().getName() + "手里的钱:" + nowMoney);
        }

    }
}