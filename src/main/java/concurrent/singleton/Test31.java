package concurrent.singleton;

import concurrent.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 线程安全的单利模式
 * 
 * 相关的文章 www.cnblogs.com/sudong-bupt/p/3433643.html
 * 
 * 更好的是才用下面的方式，既不用加锁，额能实现懒加载
 *
 * 静态内部类 https://www.cnblogs.com/kungfupanda/p/7239414.html
 * @author lijunxue
 *
 * @create 2018-04-16 22:49
 *
 **/
public class Test31 {
   
    private Test31(){
        System.out.println("sinlge test31");
    }

    private static class Inner{
        private static Test31 t = new Test31();
    }

    public static Test31 getSingle(){
        return Inner.t;
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for (int i = 0; i < ths.length; i++) {
            Thread th = new Thread(()->{
                Test31.getSingle();
            });
        }
        Arrays.asList(ths).forEach(o->o.start());
    }

    
}
