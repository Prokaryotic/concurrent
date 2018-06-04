package concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供2个方法，add.size
 *
 * 写2个线程，线程1天假10个元素到同期中，线程2实现监控元素的个数，当数到5个时，线程2给出提示并结束
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test17 {

    volatile List lists = new ArrayList();

    public  void add(Object o){
        lists.add(o);
    }

    public  int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        Test17 t = new Test17();
       new Thread(()->{
           for (int i = 0; i < 20; i++) {
               Object o = new Object();
               t.add(o);
               System.out.println((i+1));
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();

       new Thread(()->{
           while (true){
               if (5==t.size()){
                   System.out.println("t2 break");
                   break;
               }
                  
           }
       }).start();
    }
    
}
