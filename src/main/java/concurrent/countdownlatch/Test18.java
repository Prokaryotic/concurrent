package concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供2个方法，add.size
 *
 * 写2个线程，线程1天假10个元素到同期中，线程2实现监控元素的个数，当数到5个时，线程2给出提示并结束
 *
 * 给lists加上volatile后t2能够接收到通知，但是，t2线程的死循环很浪费CPU ，如果不用死循环，应该怎么做
 *
 * 这使用了wait和notify做到，wait会释放锁，而notify不会释放锁
 *
 * wait 是当你获取一个对象锁的时候才能使用的 一个线程T1比如你获取synchronized(O1)
 * 然后你做了判断 发现还不满足条件 然后你就可以调用这个O1的wait方法
 * 释放这个锁，T1进入等待，只有当这个锁被调用notify方法或者notifyall的时候才能被唤醒，
 * ----注意这里notify是不会释放锁的 所以他会等待到T1释放锁为止在执行后面的代码-----
 * 被唤醒后继续执行wait后面的代码
 *
 *
 *
 * 需要注意是是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test18 {

    volatile List lists = new ArrayList();

    public  void add(Object o){
        lists.add(o);
    }

    public  int size(){
        return lists.size();
    }

    public static void main(String[] args) {

        final Object lock = new Object();
        Test17 t = new Test17();

        // TODO 必须先启动T2线程 先让T2线程监听
        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 start");
                if (5!=t.size()){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 被唤醒后继续执行后面的方法
                System.out.println("t2 break");

            }
        }).start();

        new Thread(()->{
            synchronized (lock){
                System.out.println("t1 start");
                for (int i = 0; i < 20; i++) {
                    Object o = new Object();
                    t.add(o);
                    System.out.println("add "+i);
                    if (5 == t.size()){
                        lock.notify(); //TODO  注意这里notify是不会释放锁的 所以他会等待到T1释放锁为止在执行后面的代码
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

}