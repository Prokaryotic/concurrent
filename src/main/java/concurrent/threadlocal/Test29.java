package concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 线程局部变量
 *
 * ThreadLocal 是使用空间换时间 synchronized是使用时间换空间
 *
 * 比如在hibernate中session就存在于ThreadLocal中，避免synchronized的使用
 *
 *使用了TheadLocal就不需要加锁了 因为一个线程一份拷贝 跟其他线程的没关系了
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test29 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get()); // TODO 只有在这个线程里面的时候set  才可以在同个线程里面可以使用get方法获取到那个对象 相当于每个线程里的对象都是一份新的
        }).start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           tl.set(new Person()); // TODO 在这个线程里设置了隔着对象 在本线程中可以对这个对象进行的操作都是 跟外面线程的对象都是没有关系的
        }).start();
    }
}
class Person {
    String name = "zhangsan";
}