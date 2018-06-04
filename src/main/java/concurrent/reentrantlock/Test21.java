package concurrent.reentrantlock;




import java.util.concurrent.TimeUnit;

/**
 * reentrantlock 用于 代替 synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test21 {
    synchronized void m1(){
        System.out.println("m1 start ");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end ");
    }

    synchronized void m2(){
        System.out.println("m2 start ");
    }


    public static void main(String[] args) {
        Test21 t = new Test21();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }
}