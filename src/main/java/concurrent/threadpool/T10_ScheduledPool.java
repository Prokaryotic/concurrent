package concurrent.threadpool;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lijunxue
 * @create 2018-04-25 23:32
 **/
public class T10_ScheduledPool {


    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4); // 和原来的DelayQueue相对应 执行定时任务
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0,500,TimeUnit.MILLISECONDS);

        // 第一个参数是 Runable 任务
        // 第二个是 第一个任务需要延迟多少时间 执行
        // 第三个是 每个多少时间去执行这个任务
        // 第三个是 前面的时间单位
    }
}
