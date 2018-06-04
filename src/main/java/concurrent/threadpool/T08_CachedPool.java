package concurrent.threadpool;

import javax.xml.ws.Service;
import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lijunxue
 * @create 2018-04-25 23:31
 **/
public class T08_CachedPool {


    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        // 来一个任务就起一个线程 如果一个线程 刚执行完就提交上来一个新的任务
        // 那么原来的线程就会直接执行新提交上来的任务 如果没有线程是空闲的那么 就新起一个线程
        // 如果线程执行完任务 没有新的任务提交上来 他会等待 60 秒(默认) 到达60秒后就 自动释放 alivetime 默认60秒

        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });

        }
        System.out.println(service);
        try {
            TimeUnit.SECONDS.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(service);
    }

}
