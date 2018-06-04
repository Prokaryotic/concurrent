package concurrent;

/**
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test1 {

    public void m(){
        synchronized (this){ //TODO 相当于这个创建出来的对象 当做一个对象锁   这里不是Test1.class 拿来当对象锁
            System.out.println("666");
        }
    }
}
