package concurrent;

/**
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test3 {
    public synchronized static void m(){  //TODO  这个相当于 用synchronize(Test3.class)
        System.out.println("666");
    }
}
