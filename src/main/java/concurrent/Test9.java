package concurrent;


import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁
 * 再次申请的时候任然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * 这里是进程中有可能发生的情形，子类调用父类的同步方法
 *
 *
 * 子类new 出来的是 子类的对象O1 子类的synchronized 锁的是 this也就是 O1
 * 然后调用父类的方法 锁的还是this 还是 O1对象  this的引用是不变的
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test9 {

    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end ");
    }



    public static void main(String[] args) {
        new TT().m();
    }

}

class TT extends Test9{
    @Override
    synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");

    }
}
