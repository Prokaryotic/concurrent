package concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 使用trrntrantlock还可以指定为公平锁  公平锁是谁等的时间长让谁先来
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test25 extends Thread{
    
    private static ReentrantLock lock = new ReentrantLock(true); // 参数设置为true 表示公平锁 请对比输出结果

    @Override
    public void run() {
        for (int i = 0; i <100; i++) {
             lock.lock();
            System.out.println(Thread.currentThread().getName()+"get lock");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test25 t1 = new Test25();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t1);
        thread1.start();
        thread2.start();
    }
}
