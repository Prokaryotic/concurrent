package concurrent;

import java.util.concurrent.TimeUnit;

/**
 *
 * 程序在执行的过程中如果出现异常默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能乎发生不一致的情况
 * 比如在，web app 处理过程中，多个servlet线程共同访问同一个资源，这是如果异常处理不合适
 * 在第一个线程抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生是的数据
 * 因此要非常小心的处理同步业务逻辑中的异常
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test10 {

    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5){
                int i = 1/0;  //TODO 此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch然后让循环继续
                // TODO 如果我在这里要重新做 上面这个任务 就要通过事务回滚  例如用 catch 然后重新做这个任务

            }
        }
    }

    public static void main(String[] args) {
     Test10 t = new Test10();
     new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m,"t2").start();
    }


}
