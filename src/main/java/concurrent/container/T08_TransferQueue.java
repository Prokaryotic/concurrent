package concurrent.container;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author lijunxue
 * @create 2018-04-25 20:50
 **/
public class T08_TransferQueue {
    public static void main(String[] args) {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            strs.transfer("aaa"); // transfer方法把消息直接人给消费者 如果没有消费那么 会在这里阻塞 这个跟put不一样
            // TODO 注意这里是 是没有消费者的时候 进行的阻塞 不是由于队列满导致的阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();






    }


}
