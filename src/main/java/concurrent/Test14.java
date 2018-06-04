package concurrent;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 *
 * synchronized 优化
 * 同步代码块中的语句越少越好
 * 比较m1和m2
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test14 {
    int count = 0;
    synchronized void m1(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count++;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TODO  业务逻辑中只有下面代码需要synchronized 这是不应该给整个方法上锁
        //TODO  才用细粒度的锁，可以是线程竞争使用时间变短，从而提高效率
        synchronized (this){
            count ++;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
