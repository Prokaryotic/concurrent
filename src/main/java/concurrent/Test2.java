package concurrent;

/**
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test2 {

    public synchronized void m(){  //TODO  这个相当于 用创建出来的对象来当 对象锁 等价于 synchronize(this)
        System.out.println("666");
    }
}
