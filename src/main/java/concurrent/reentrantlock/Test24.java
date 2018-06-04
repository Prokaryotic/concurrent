package concurrent.reentrantlock;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用reentrantlock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个子线程等待所的过程中，可以被打断
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test24 {

    Lock lock = new ReentrantLock();
    void m1(){

        try {
            System.out.println("m1 start ");
            lock.lock();
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    void m2(){
        boolean locked = true;
        try {

            // 可以对interrupt方法做出响应 然后打断自己的等待
            // TODO 注意不会继续执行下面的代码而是直接跳到异常中
            lock.lockInterruptibly();
            System.out.println("m2");

        } catch (InterruptedException e) {
            locked = false;     // 外部调用线程的interrupt方法  会进入到这边
            System.out.println("interrupted");
        } finally {
            if (locked){
                lock.unlock();
            }
        }


    }


    public static void main(String[] args) throws InterruptedException {
        Test24 t = new Test24();
        Thread thread1 = new Thread(t::m1);
        thread1.start();
        Thread thread2 = new Thread(t::m2);
        thread2.start();
        TimeUnit.SECONDS.sleep(5);
        thread2.interrupt();
    }
}