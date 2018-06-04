package concurrent;

/**
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test {
    private Object o = new Object();
    public void m(){
        synchronized (o){ //TODO  任何线程要执行以下代码必须先拿到这个对象锁
            System.out.println("6666");
        }
    }
    
}
