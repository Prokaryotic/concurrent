package concurrent.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用reentrantlock可以进行“尝试锁定”trylock，这样无法锁定，
 * 或者在指定时间内无法锁定，线程就可以决定是否继续等待
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test23 {

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
        boolean locked = false;
        try {
//            locked = lock.tryLock(); // 尝试锁定 如果没有获取锁 那么就不等待
            locked = lock.tryLock(5,TimeUnit.SECONDS);// 这里指定 等待时间 在5秒内如果还没有获取到锁那么就不再等待
            System.out.println("m2 " +locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked){
                lock.unlock();
            }
        }


        lock.unlock();
    }


    public static void main(String[] args) {
        Test23 t = new Test23();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }
}