package concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lijunxue
 * @create 2018-04-25 23:53
 **/
public class T05_ThreadPool {
    public static void main(String[] args) {
        ExecutorService service =  Executors.newFixedThreadPool(5); // 固定分数的线程池实现了ExecutorService 接口
        // ExecutorService 接口 实现了execute 只能执行Runable任务
        // 和 submit 能提交Runable和Callable任务
        for (int i = 0; i < 6; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
            System.out.println(service);
            service.shutdown();
            System.out.println(service.isTerminated()); // 所有的任务是不是全部执行完了
            System.out.println(service.isShutdown()); // 线程池是不是正在关闭过程中(要等任务执行完)
            System.out.println(service);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(service.isTerminated());
            System.out.println(service.isShutdown());
            System.out.println(service);
        }
    }
}
