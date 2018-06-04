package concurrent.threadpool;

import java.util.concurrent.*;

/**
 * FutureTask 是一个Callable的包装类
 *
 * 可以让Thread执行Callable的call方法 并且提供一个阻塞的get方法返回一个执行返回的类T
 *
 * @author lijunxue
 * @create 2018-04-25 23:30
 **/
public class T06_Future {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> task = new FutureTask<Integer>(() -> {
            TimeUnit.MILLISECONDS.sleep(500); // 这里是实现Callable的call方法
            return 1000;
        });

        new Thread(task).start();


        System.out.println(task.get());  // 是阻塞的

/**********************************************/
// 也可以通过 线程池来提交Callable类的包装类Future任务

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

        System.out.println(f.get()); // 是阻塞的
        System.out.println(f.isDone());// 非阻塞

    }
}
