package concurrent.container;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 每当添加一个新的数据的时候 会把所有的数据复制到一个新的容器里 并添加这二个新增加的 把引用指向新的
 * 对于读操作来讲 效率非常高因为不用加锁了不会产生脏数据
 * <p>
 * 多线程环境下 写时效率低 读是效率高
 * 适合写少 读多的环境
 *
 * @author lijunxue
 * @create 2018-04-24 22:56
 **/
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> list =
//            new ArrayList<>();  // 会有并发问题
//        new Vector<>(); //没有并发问题
                new CopyOnWriteArrayList<>(); // 写数据贼耗费时间 是其他的20倍左右 读的时候是最快的 适合监听器
        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.add("a" + r.nextInt(100000));
                }
            });
        }
        runAndComputeTime(ths);
        System.out.println(list.size());

    }


    static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join(); // 等待改线程停止
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("use : " + (end - start) / 1000);
    }
}
