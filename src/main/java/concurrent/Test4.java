package concurrent;

/**
 *
 * 一个synchronized的代码块是原子操作 是不可分的
 *
 * @author lijunxue
 * @create 2018-04-16 22:49
 **/
public class Test4 implements Runnable{

    private int count = 10;

    public synchronized void run(){
        count -- ;
        System.out.println(Thread.currentThread().getName()+"count :" +count);
    }


    public static void main(String[] args) {
        Test4 t = new Test4();
        for (int i = 0; i < 5; i++) {
           new Thread(t,"THREAD"+i).start();

        }
    }
}
