package concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *vvolatile 不保证原子性      效率比synchronized 高
 *
 * 比如下面的程序 就是只保证读的时候是可见的 但是写到主线程的时候 不会去管主内存里的东西变了
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test12 {
    volatile int count = 0;
    void m(){
        for (int i = 0; i < 10000; i++) {
            count++;

        }
    }

    public static void main(String[] args) {
        Test12 t = new Test12();
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
