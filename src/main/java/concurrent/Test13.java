package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test13 {
    AtomicInteger count = new AtomicInteger(0);   // 使用了无锁   是原子性的

    void m(){
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet(); // 每次读的时候 都会去看原来的值会变
            // 如果变了话那么就会在读 直到读到的值是期望值 然后再对这个值进行加1



            // TODO 下面是2个Atomic系列的操作 这2个结合的不具备原子性
            // TODO 虽然这2个单独的时候都是原子性的操作 但是他们之间 还是会有线程 进来
//            if(count.get()<10){ // TODO 比如这边只值还有9 是满足条件的 返回的是 9<10 是true
            // TODO 然后进入这里 但是刚好有另一个线程 他是在你进行这步操作之前判断的 是判断了9<10 然后他比你早执行 或者和你一样进入了这里
            // TODO 这样就会有2次加一 导致这个操作不在具有原子性
//                count.incrementAndGet();
//            }


        }
    }

    public static void main(String[] args) {
        Test13 t = new Test13();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < threads.size(); i++) {
            threads.add(new Thread(t::m,"thread -"+i));

        }
        threads.forEach((o)->o.start());
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
