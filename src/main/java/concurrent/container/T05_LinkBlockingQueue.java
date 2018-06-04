package concurrent.container;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 消费者生产者 阻塞式的
 * @author lijunxue
 * @create 2018-04-24 23:27
 **/
public class T05_LinkBlockingQueue {
    // LinkedBlockingDeque是双向的link
    static BlockingQueue<String> strings = new LinkedBlockingQueue<>(); // 阻塞式的 队列 link是无界的
    static Random r = new Random();
    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    strings.put("a" +i); // put 如果满了就会等待
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (;;){
                    try {
                        System.out.println(Thread.currentThread().getName()+"take - "+strings.take()); // take 如果空了就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();

        }
    }
}
