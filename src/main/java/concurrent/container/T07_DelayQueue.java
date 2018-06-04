package concurrent.container;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

/**
 * @author lijunxue
 * @create 2018-04-24 23:48
 **/
public class T07_DelayQueue {
    static BlockingQueue<MyTask> tasks = new DelayQueue(); // 无界队列 每个队列等待时间最长的往外先拿
    static Random random = new Random();

    static class MyTask implements Delayed {
        long runningTime;

        public MyTask(long runningTime) {
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit timeUnit) { // 还有多少时间可以往外拿了
            return timeUnit.convert(runningTime - currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayed) {
            // 比大小 这个比其他的小就是-1的话就是从小到大 -1代表这个放到前面
            // 还可以 这个比其他的大为1 那就是从大到小 1代表放到后面
            if (this.getDelay(TimeUnit.MILLISECONDS) < delayed.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > delayed.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        long now = currentTimeMillis();
        MyTask task1 = new MyTask(now + 1000); // 从现在开始之后1秒执行
        MyTask task2= new MyTask(now + 2000);
        MyTask task3 = new MyTask(now + 1500);
        MyTask task4 = new MyTask(now + 2500);
        MyTask task5 = new MyTask(now + 500);
        tasks.put(task1);
        tasks.put(task2);
        tasks.put(task3);
        tasks.put(task4);
        tasks.put(task5);
        System.out.println(tasks); // 加入任务 的时候其实 是排好时间的 这个可以做定时任务
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take()); // 往外拿任务

        }
    }
}
