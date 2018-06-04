package concurrent.threadpool;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 任务窃取算法
 * @author lijunxue
 * @create 2018-04-25 23:32
 **/
public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool(); // 默认是多少核的cpu起多少个线程
        System.out.println(Runtime.getRuntime().availableProcessors()); // 看一下cpu 是多少核的

        service.execute(new R(1000)); // 这里 产生的是deman 线程 是精灵线程 又叫守护线程 又叫 后台线程
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        // 由于产生的是精灵线程(守护线程、后台线程)， 主线程不阻赛的话，看不到输出
        System.in.read();
    }


    static class R implements Runnable {
        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + "" + Thread.currentThread().getName());
        }
    }
}
