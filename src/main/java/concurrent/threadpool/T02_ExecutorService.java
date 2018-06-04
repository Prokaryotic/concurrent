package concurrent.threadpool;

/**
 * 查看 ExecutorService 接口
 *
 * @author lijunxue
 * @create 2018-04-25 23:25
 *
 **/
public class T02_ExecutorService {

//    方法摘要
//    boolean awaitTermination(long timeout, TimeUnit unit)
//    请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行。
//    <T> List<Future<T>>
//    invokeAll(Collection<? extends Callable<T>> tasks)
//    执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。
//    <T> List<Future<T>>
//    invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
//    执行给定的任务，当所有任务完成或超时期满时（无论哪个首先发生），返回保持任务状态和结果的 Future 列表。
//    <T> T
//    invokeAny(Collection<? extends Callable<T>> tasks)
//    执行给定的任务，如果某个任务已成功完成（也就是未抛出异常），则返回其结果。
//    <T> T
//    invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
//    执行给定的任务，如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果。
//    boolean isShutdown()
//    如果此执行程序已关闭，则返回 true。
//    boolean isTerminated()
//    如果关闭后所有任务都已完成，则返回 true。
//    void shutdown()
//    启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
//    List<Runnable> shutdownNow()
//    试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
//    <T> Future<T>
//    submit(Callable<T> task)
//    提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。
//    Future<?> submit(Runnable task)
//    提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
//    <T> Future<T>
//    submit(Runnable task, T result)
//    提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。

}
