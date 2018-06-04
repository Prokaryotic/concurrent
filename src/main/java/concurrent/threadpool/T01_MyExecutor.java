package concurrent.threadpool;

import java.util.concurrent.Executor;

/**
 * Executor 执行器 顶层接口
 *
 * @author lijunxue
 * @create 2018-04-25 23:14
 **/
public class T01_MyExecutor implements Executor{
    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println("hello executor"));
    }

    @Override
    public void execute(Runnable runnable) {

        runnable.run();
    }
}
