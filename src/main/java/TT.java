/**
 * @author lijunxue
 * @create 2018-04-27 10:01
 **/
public class TT {
    public static void main(String[] args) {
        String s = "     * Bit field accessors that don't require unpacking ctl.\n" +
                "     * These depend on the bit layout and on workerCount being never negative.";
        System.out.println(s.replace("     ","").replace("*","").replace("\n",""));
    }
}
