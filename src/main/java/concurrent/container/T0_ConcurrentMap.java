package concurrent.container;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * ConcurrentSkipListMap 外部阅读 blog.csdn.net/sunxianghuang/article/details/52221913
 *
 * @author lijunxue
 * @create 2018-04-23 23:24
 **/
public class T0_ConcurrentMap {

    public static void main(String[] args) {
//        Map<String, String> map = new ConcurrentHashMap<>();  // 703耗时 默认把同期细化成16段每次 插入的时候 只锁定一段 1.8之后用了CAS 无锁
//        Map<String,String> map = new ConcurrentSkipListMap<>();// 高并发 并且插入的数据是排序的

        Map<String, String> map = new Hashtable<>(); // 是线程安全的 786耗时
//        Map<String,String> map = new HashMap<>();
        // TreeMap

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("use : " + (end - start) / 1000.0);
    }
}
