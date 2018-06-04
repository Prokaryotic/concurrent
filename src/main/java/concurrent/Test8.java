package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 模拟死锁  交叉要锁
 * @author lijunxue
 * @create 2018-04-16 23:49
 **/
public class Test8 {

    public class A{
        synchronized void m1(){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1");
            B b = new B();
            b.m2();
        }
    }

    public class B{
        synchronized void m2(){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            A a = new A();
            a.m1();
            System.out.println("m2");

        }
    }

    public void doA(){
        A a = new A();

        a.m1();

    }
    public void doB(){

        B b = new B();
        b.m2();

    }


    public static void main(String[] args) {
        Test8 t = new Test8();
     new Thread(t::doA).start();
     new Thread(t::doB).start();


    }

}
