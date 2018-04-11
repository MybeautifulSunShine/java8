package cn.test.java8;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/7 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class FutureInAction {
    public static void main(String[] args) throws InterruptedException {
    //采用future的方式,他会先得到结果
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        //.....做一些操作
        //....
        //如果操作没有完成就等待
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        System.out.println(future.get());
    /*    String value = block(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println(value);*/
    }
    //采用block的方式他会一直等到结束
    /*private static <T> T block(Callable<T> callable) {
        return callable.action();
    }*/
    //自定义一个future并理解思想
    //调用一个方法但是时间很长 想立即得到一个结果
    private static <T> Future<T> invoke(Callable<T> callable) {
        //原子操作
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        t.start();

        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };

        return future;


    }

    //定义两个接口
    private interface Future<T> {
        //获取到
        T get();
        //是否执行完
        boolean isDone();
    }

    private interface Callable<T> {
        //回调的动作
        T action();
    }
}
