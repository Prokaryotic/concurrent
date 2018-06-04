package concurrent.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author lijunxue
 * @create 2018-04-25 21:48
 **/
public class T09_SynchronusQueue { // 容量为0
    public static void main(String[] args) {
        BlockingQueue<String>strings = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strings.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            strings.put("aaa");// TODO 你能调用put 但是他会一直等待 等待消费者消费 里面实现是trans 就是直接给消费者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        strings.add("aaa"); // TODO 你不能调用add 因为这个队列的容量是0 你如果是用add那么 将会报错 queue is full
        System.out.println(strings.size());

    }
}
