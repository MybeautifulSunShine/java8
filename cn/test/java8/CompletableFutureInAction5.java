package cn.test.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction5 {
    public static void main(String[] args) throws InterruptedException {
        //两个都结束之后去执行的动作  里面也是Runnable的接口
       /* CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            //是一个后台线程也就是一个守护线程
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            return 2;
        }), () -> System.out.println("done"));*/


        //其中的一个执行完 传递给一个function的interfacer 就执行动作,v -> v * 10
      /*  CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), v -> v * 10)
                //把结果交给accep进行下一步的计算就ok了
                .thenAccept(System.out::println);*/
//                Thread.currentThread().join();//主线程中断信号


        //acceptEither 那个运算结果直接消费,不会因为他是一个consumer
        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
            //两个执行完后,直接去消费不会在去 function
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
            //那个计算结束就打印那个
        }), System.out::println);*/


        //运行结束之后执行一个runnable
        //跟上面的用法一样只不过一个会消费,另外一个是传递给了Runnable
        //可以做一些通知的结果,也就是两个运算完之后可以执行一些操作,写日志等等
       /* CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), () -> System.out.println("done."));*/

       /* List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(toList());

        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("done"));*/

      /* List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4).stream().map(i ->
                CompletableFuture.
                        supplyAsync(CompletableFutureInAction1::get)).collect(toList());
       //变成数组
        CompletableFuture[] array = collect.toArray(new CompletableFuture[collect.size()]);
        //等到都执行完之后做一些事情(要的是一个数组)
        CompletableFuture.allOf(array).thenRun(()->System.out.println("fone"));
        // 把它变成数组
*/

        Thread.currentThread().join();
    }
}
