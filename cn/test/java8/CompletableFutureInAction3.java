package cn.test.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction3 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

       /* CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
                .thenApply(CompletableFutureInAction3::multiply)
                .whenComplete((v, t) -> Optional.ofNullable(v).ifPresent(System.out::println));*/

        List<Integer> productionIDs = Arrays.asList(1, 2, 3, 4, 5);
        /*List<Double> result = productionIDs
                .stream()
                .map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor))
                .map(future -> future. (CompletableFutureInAction3::multiply))
                .map(CompletableFuture::join).collect(toList());

        System.out.println(result);*/
        //做成5个同时并发去做 也就是每个线程都执行一次最终放到结果里面来    放到一个并行里面去执行  高并发执行过程
        Stream<CompletableFuture<Double>> futureStream = productionIDs.stream().map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor));
        //然后在去计算 也就是乘以10
        Stream<CompletableFuture<Double>> stream = futureStream.map(future -> future.thenApply(CompletableFutureInAction3::multiply));
        //join 等到所有结果都执行结束 在转化为list
        List<Double> list = stream.map(CompletableFuture::join).collect(toList());
        System.out.println(list);
    }

    private static double multiply(double value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return value * 10d;
    }

    private static double queryProduction(int i) {
        return CompletableFutureInAction1.get();
    }
}
