package concurrent.container;


import java.util.Queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lijunxue
 * @create 2018-04-24 23:16
 **/
public class T04_ConcurrentQueue {



    public static void main(String[] args) {
        Queue<String> strings = new ConcurrentLinkedQueue<>(); // 这是无界队列 是单向的
        for (int i = 0; i < 10; i++) {
            strings.offer("a"+i); // 相当于List里面的add 可以通过返回值判断是否成功添加元素

        }
        System.out.println(strings);
        System.out.println(strings.size());
        System.out.println(strings.poll()); // 从头拿出一个 就是下标0的元素 拿出来的时候就删掉了容器里的
        System.out.println(strings.peek()); // 从头拿出一个 就是下标0的元素 拿出来的时候就不会删掉容器里的
        System.out.println(strings.size());

        // 双向队列Deque   ConcurrentLinkedDeque
    }
}
