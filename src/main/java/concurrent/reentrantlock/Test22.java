package concurrent.reentrantlock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock 用于 代替 synchronized  手工锁 必须手动锁定 和 释放
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 *
 * 使用reentrantlock必须要手动释放锁
 * 使用syn宋丁的话如果遇到异常jvm会自动释放，但是lock必须要手动释放，所以经常在finally中进行锁的释放
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test22 {

    Lock lock = new ReentrantLock();
     void m1(){

        try {
            System.out.println("m1 start ");
            lock.lock();
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

     void m2(){
         lock.lock();
        System.out.println("m2 start ");
        lock.unlock();
    }


    public static void main(String[] args) {
        Test22 t = new Test22();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }
}