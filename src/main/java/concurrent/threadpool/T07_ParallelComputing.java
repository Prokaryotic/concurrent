package concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static concurrent.threadpool.T07_ParallelComputing.MyTask.getPrime;

/**
 * 并行计算
 * 线程池的概念
 *
 * @author lijunxue
 * @create 2018-04-25 23:30
 **/
public class T07_ParallelComputing {

    public static void main(String[] args) throws Exception {

        // 使用给一个主线程来执行 1-200000之间有几个质数的任务 耗时
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);

        // 使用给四个线程来执行 1-200000之间有几个质数的任务 耗时
        final int cpuCoreNum = 4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum); // 固定个数的线程池

        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }

        static boolean isPrime(int num) {
            for (int i = 2; i < num / 2; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

        static List<Integer> getPrime(int start, int end) {
            List<Integer> r = new ArrayList<>();
            for (int i = start; i < end; i++) {
                if (isPrime(i)) {
                    r.add(i);
                }
            }
            return r;
        }
    }
}
