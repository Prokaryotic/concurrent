package concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * 线程局部变量 threadlocal
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test28 {
    volatile static Person p = new Person();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "leee";
        }).start();
    }



}
