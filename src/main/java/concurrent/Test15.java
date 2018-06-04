package concurrent;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某个对象O1 如果O1的属性发生了改变 不影响锁的使用
 * 但是如果O1的引用变成了另一个对象 则锁定的对象发生了变化
 * 应该避免将锁定对象的引用变成另外的对象
 *
 *
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test15 {

    Object o1 = new Object();

    void m(){
        synchronized (o1){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            }
        }
    }

    public static void main(String[] args) {

        Test15 t = new Test15();
        // TODO 启动第一个线程
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(t::m,"t2");
        t.o1 = new Object();  // TODO  锁的对象发生了改变，所以thread2线程获得了锁，如果注释掉这句话，线程2将永远得不到执行机会
        // TODO 启动第二个线程
        thread2.start();

        // TODO 从结果上来看 这说明了synchronized是根据堆内存里的对象来锁的  而不是栈里面的引用reference这个类型

    }

}
