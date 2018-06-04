package concurrent.notifyall;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个 固定容量同步容器 拥有put和get方法 以及getCount方法
 * <p>
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用Lock和Condition来实现
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test27<T> {
    final private LinkedList<T> list = new LinkedList<>();
    final private int Max = 10; // 最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();  // 创建专门的条件然他们等待 或 唤醒
    private Condition consumer = lock.newCondition();

    public void put(T t) {

        // 想想为什么用while而不是if
        // 1. 因为 有消费者消费过后 唤醒他 他可能没有立即获取锁
        // 而是 在其他put线程执行完后才执行 但是如果没有while 那么他就不会进行判断会直接给list 添加一个元素
        // 2. 因为 其他的put线程也会notifyAll 导致被唤醒

            lock.lock();
            while (list.size() == Max) {
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
            ++count;
            consumer.signalAll(); // 通知消费者线程进行消费 这里比较好 可以指定通知消费者


    }

    public  T get() {
        T t = null;
        lock.lock();
        while (list.size() == 0) {
            try {
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t = list.removeFirst();
        count--;
        producer.signalAll();
        return t;
    }

    public static void main(String[] args) {
        Test26<String> t = new Test26<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(t.get());
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    t.put(Thread.currentThread().getName() + " " + j);
                }
            }).start();

        }

    }
}
