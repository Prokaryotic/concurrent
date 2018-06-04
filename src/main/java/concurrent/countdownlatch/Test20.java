package concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供2个方法，add.size
 * <p>
 * 写2个线程，线程1天假10个元素到同期中，线程2实现监控元素的个数，当数到5个时，线程2给出提示并结束
 * <p>
 * <p>
 * <p>
 * 使用Latch(门闩)代替wait notif来进行通知
 * 好处是通信方便，同事也可以指定等待时间
 * 使用await和countdown 方法代替wait和notify
 * CountDownLatch不涉及锁定，当count的值为0时当前线程继续运行
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized+wait/notify就显的太重了
 * 这时应该考虑countdownlatch/cyclicbarrier/semaphore
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test20 {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1); //TODO  当1变成0的时候 门闩就开了

        Test17 t = new Test17();

        // TODO 必须先启动T2线程 先让T2线程监听
        new Thread(() -> {
            System.out.println("t2 start");
            if (5 != t.size()) {
                try {
                    latch.await();
                    // TODO 也可以指定时间
//                    latch.await(5000,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 被唤醒后继续执行后面的方法
            System.out.println("t2 break");
        }).start();

        new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 20; i++) {
                Object o = new Object();
                t.add(o);
                System.out.println("add " + i);
                if (5 == t.size()) {
                    latch.countDown(); //TODO  减一使1变为0，让await的门闩打开 T2 就继续执行
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}