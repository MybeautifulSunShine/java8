package cn.test.java2;


import java.util.concurrent.ExecutorService;

/**
 * Created by SJ217110601 on 2018/3/14.
 */
public class DefauultInAction{
    public static void main(String[] args) {
        A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());
    }

    private interface A {
        int size();

        default boolean isEmpty() {
            return size() == 0;
        }
        //future 异步
//        ExecutorService //线程池里面参生的
    }
}
