package concurrent.threadpool;

/**
 *
 * Callable 任务运行的时候调用的是 call方法 而 Runable任务运行的时候是run方法
 *
 * 但是Callable 的call方法是有返回值的 也就是他是阻塞的
 *
 * V call() 计算结果，如果无法计算结果，则抛出一个异常。
 *
 * 而Runable 的 run 是没有返回值的 也就是说他是非阻塞的
 *
 * void run() 使用实现接口 Runnable 的对象创建一个线程时，启动该线程将导致在独立执行的线程中调用对象的 run 方法。 不会抛出异常
 *
 * @author lijunxue
 * @create 2018-04-25 23:29
 **/
public class T03_Callable {
}
