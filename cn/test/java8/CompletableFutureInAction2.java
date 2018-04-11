package cn.test.java8;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction2 {

    public static void main(String[] args)
            throws InterruptedException {
        AtomicBoolean finished = new AtomicBoolean(false);
        //工程接口
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });
        //没有入参但是有一个返回值
        //用supplyAsync  来创建一个同步的future
        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                    finished.set(true);
                });

        System.out.println("====i am no ---block----");
        //如果没结束
/*        while (!finished.get()) {
            Thread.sleep(1);
        }*/
    }
}
