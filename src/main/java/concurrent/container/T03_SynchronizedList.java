package concurrent.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lijunxue
 * @create 2018-04-24 23:14
 **/
public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        List<String> strsSyn = Collections.synchronizedList(strs); // 给list加锁

    }
}
