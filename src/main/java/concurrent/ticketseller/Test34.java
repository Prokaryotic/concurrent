package concurrent.ticketseller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test34 {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() > 0) break;
                        System.out.println("销售了 ：" + tickets.remove(0));
                    }
                }

            }).start();

        }
    }
}
