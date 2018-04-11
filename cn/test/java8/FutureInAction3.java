package cn.test.java8;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/7 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class FutureInAction3 {

    public static void main(String[] args) {
        //给future增加一个回掉时间,就是如果执行结束直接通知就ok了

        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000L);
                return "I am Finished.";
            } catch (InterruptedException e) {
                return "I am Error";
            }
        });


        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("error");
                cause.printStackTrace();
            }
        });
        System.out.println(".........");
        System.out.println(future.get());
        System.out.println(future.get());
    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Future<T> future = new Future<T>() {
            private Completable<T> completable;

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
            //有值了就回调这个方法
            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }
            //没有就回调这个方法
            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };
        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                //如果获取到东西的操作
                if (future.getCompletable() != null)
                    future.getCompletable().complete(value);
            } catch (Throwable cause) {
                if (future.getCompletable() != null)
                    future.getCompletable().exception(cause);
            }
        });
        t.start();

        return future;
    }


    private interface Future<T> {

        T get();

        boolean isDone();
        //回调函数
        void setCompletable(Completable<T> completable);
        //构造函数
        Completable<T> getCompletable();
    }
    //回调函数
    private interface Callable<T> {
        T action();
    }

    private interface Completable<T> {
        //有值了就回调这个方法
        void complete(T t);
        //没有就抛出异常
        void exception(Throwable cause);
    }
}
