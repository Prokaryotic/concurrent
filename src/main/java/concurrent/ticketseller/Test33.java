package concurrent.ticketseller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 使用Vector同步容器
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test33 {
    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0){ // TODO 还是前面一样的问题 tickets.size()是原子的
                    // TODO  但是和下面合起来的时候就不是了 所以还是不能解决问题
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了 ：" +tickets.remove(0));
                }
            }).start();

        }
    }
}