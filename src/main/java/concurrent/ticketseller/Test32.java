package concurrent.ticketseller;

import java.util.ArrayList;
import java.util.List;

/**
 * 有N张火车票 每张票都有一个编号
 * 同事有10个窗口对外售票
 * 请写一个模拟程序
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test32 {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票号： " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0){ // TODO 这里有问题 取票的时候 会重复取票 因为list不是同步的
                    System.out.println("销售了 ：" +tickets.remove(0));
                }
            }).start();

        }
    }
}
