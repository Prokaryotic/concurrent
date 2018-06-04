package concurrent;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import java.util.concurrent.TimeUnit;

/**
 *
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 容易产生脏读的问题(dirtyRead)
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test6 {
    String name ;
    double balance;

    public synchronized void set(String name,double balance){
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {

        Test6 t = new Test6();
        new Thread(()->t.set("zhangsan",100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getBalance());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getBalance());
    }
}
