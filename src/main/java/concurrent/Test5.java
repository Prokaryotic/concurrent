package concurrent;

/**
 *
 * 如果m1方法是加了this的对象锁 那么没有对象锁的m2能不能启动
 *
 * 能启动
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test5 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end...");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2");
    }


    public static void main(String[] args) {
        final Test5 t = new Test5();
        new Thread(t::m1,"T1").start();
        new Thread(t::m2,"T2").start();
    }


}
