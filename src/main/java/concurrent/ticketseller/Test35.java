package concurrent.ticketseller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 使用并发容器ConcurrentLinkedDeque
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test35 {
    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true){
                    String s = tickets.poll(); //TODO 从队列里面先获取 和Vector最大的不同是假如队里没了东西 Vector是会报错的 而ConcurrentLinkedDeque会获取空
                    if (s == null) break;
                    System.out.println("销售了 ：" +tickets.remove(0));
                }
            }).start();

        }
    }
}
