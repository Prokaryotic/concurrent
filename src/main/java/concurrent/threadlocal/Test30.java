package concurrent.threadlocal;

import java.util.concurrent.TimeUnit;
// TODO 只有在这个线程里面的时候set  才可以在同个线程里面可以使用get方法获取到那个对象 相当于每个线程里的对象都是一份新的

// TODO 在这个线程里设置了隔着对象 在本线程中可以对这个对象进行的操作都是 跟外面线程的对象都是没有关系的

/**
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test30 {
    static ThreadLocal<People> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        People people = new People();
        people.setName("666");
        new Thread(() -> {
            People p;
            tl.set(people);
            p = tl.get();
            p.setName("23333");
            System.out.println(" set people name 23333");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + tl.get().getName()); // 这里已经发生了改变
        }).start();
        System.out.println(Thread.currentThread().getName() + " " +people.getName()); // 这输出原始的people.getName();  还是没有变化

        new Thread(() -> {
            try {
                People p = tl.get();// 这里获取不到  因为每个线程的ThreadLocal 里面设置的对象 都是独立的 相当于配个都是一份新的完全独立的对象 不会对其他的线程的对象做什么影响
                TimeUnit.SECONDS.sleep(5);
                System.out.println(" get people name" + p.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}