package concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lijunxue
 * @create 2018-04-25 23:31
 **/
public class T09_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool(); // 不管几个任务都是 一个线程来完成 保证任务 的选后顺序性
        for (int i = 0; i < 5; i++) {
           final int j = i;
           service.execute(()->{
               System.out.println(j+" " +Thread.currentThread().getName());
           });

        }
    }
}
