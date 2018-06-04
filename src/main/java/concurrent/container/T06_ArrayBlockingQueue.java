package concurrent.container;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 消费者生产者 阻塞式的
 *
 * @author lijunxue
 * @create 2018-04-24 23:27
 **/
public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strings = new ArrayBlockingQueue<String>(15); // 阻塞式的 队列 array是有界的 有固定的容量
    static Random r = new Random();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            strings.put("A" + i);
        }

        // TODO 放入
//        strings.put("aaa");  //put 是阻塞的 当容器满的时候 如果不能添加进去就会等待
//        strings.add("aaa");  // 当容器满的时候  加入会导致 报错 queue full
//        strings.offer("aaa");  //当容器满的时候  加入会导致 添加不成功 不会报错 会返回一个boolean的值 来说明是否成功
//        strings.offer("aaa",1,TimeUnit.SECONDS);  // 1秒钟之后加不进去不添加了 添加不成功 不会报错 会返回一个boolean的值 来说明是否成功

        // TODO 拿出
//        strings.poll(); // 非阻塞  获取并移除此队列的头部，在指定的等待时间前等待可用的元素（如果有必要） 若超出了等待时间 则返回null。
//        strings.take(); // 阻塞 获取并移除此队列的头部，在指定的等待时间前等待可用的元素（如果有必要）一直等待 知道有可用元素进来。
//        strings.peek();// 非阻塞  获取队列的头部(不移除)，在指定的等待时间前等待可用的元素（如果有必要） 若超出了等待时间 则返回null。
        System.out.println(strings);
        for (int i = 0; i < 11; i++) {
//            strings.poll(1,TimeUnit.SECONDS);
            strings.take();
            System.out.println("i:"+i);
        }
        System.out.println(strings);
    }
}
