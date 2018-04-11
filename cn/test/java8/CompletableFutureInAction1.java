package cn.test.java8;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction1 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args)
            throws ExecutionException, InterruptedException {

        //supplyAsync
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            double value = get();
            //回调方法
            completableFuture.complete(value);
        }).start();
        //立即得到了执行
        System.out.println("===no===block....");
        //还是阻塞的方式  因为他会block住
//        Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
        //计算完之后直接通知  不用向上面这样 get()去哪拿
        completableFuture.whenComplete((v, t) -> {//算完去通知里面有两个参数,一个是结果一个是异常信息
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());//打印异常信息
        });
    }

    static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = RANDOM.nextDouble();
        System.out.println(result);
        return result;
    }
}
