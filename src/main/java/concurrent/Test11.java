package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在多个线程间可见  没有保证原子性
 * A B 2个线程动用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都督导变量的修改值
 * <p>
 * 在下面的代码中 running 是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running的值从内存督导线程t1的工作区，在运行过程中直接使用这个copy，
 * 并不会每次都去读堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile，
 * <p>
 * 可以阅读 http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile 并不能保证多个线程共同修改running变量是所带来的不一致问题，也就是说volatile不能代替synchronized
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test11 {
/*volatile*/ boolean running = true; // 对比一下有无volatile的情况下 整个程序运行结果的区别
    void m(){
        System.out.println(" m start ");
        while (running){

        }
        System.out.println("m end ");

    }

    public static void main(String[] args) {
        Test11 t = new Test11();
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
