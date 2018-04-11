package cn.test.java8;

import java.util.concurrent.CompletableFuture;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction4 {

    public static void main(String[] args) throws InterruptedException {

        //thenApply
        //他是守护线程
     /*   CompletableFuture.supplyAsync(() -> 1) //异步的supper通过若干种计算得出的结果是1
                .thenApply(i -> Integer.sum(i, 10))//里面是一个Action 给出一个10 返回的参数
                .whenComplete((v, t) -> System.out.println(v));//计算结束之后输出*/
                //whenCompleteAsync  也是一个异步的就是说拿到结果后还可以进行一个异步的去计算
        //handle
      /*  CompletableFuture.supplyAsync(() -> 1) //提交了一个异步任务返回了一个1
                .handle((v, t) -> Integer.sum(v, 10))//里面多了一个对异常的处理  对结果的处理,也就是增加一个10
                .whenComplete((v, t) -> System.out.println(v))
                //整个运算算完之后 的操作 里面是一个Runable
                .thenRun(System.out::println); *///run方法没有入参
                //thenRunAsync如果想做成异步的
        //thenAccept
      /*  CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                 //只是对结果进行消费,没有返回值 是一个consumer
                .thenAccept(System.out::println); //直接消费掉不会对结果进行操作
                */
        // thenCompose 对输出结果交给另外的一个CompleteFuture对它进行工作
    /*    CompletableFuture.supplyAsync(() -> 10)
               // i是前面结果的返回值 () -> 10  把i交给另外的一个CompletableFuture进行计算 forkjoin进行计算
                //对两个Completable进行组合  组合设计模式
                 //对上面的输出结果交给另外的一个CompletableFuture进行工作
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);*/

     /*   CompletableFuture.supplyAsync(() -> 1)
                //把计算结果交给了一个function
                // r1 biFunction里面的第一个参数第一个入参是CompletableFuture.supplyAsync(() -> 1) 的 结果,
                // r2第二个参数就是CompletableFuture.supplyAsync(() -> 2.0d)
                //吧他两相加
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);*/

      /*  CompletableFuture.supplyAsync(() -> 1)
                //跟上面的一样也就是返回一个void类型的  里面是一个consumer也就是说直接进行消费
                //没有返回结果,不会传下去,返回的结果是void 但是可以做别的操作,比如说run
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                    System.out.println(r1 + r2);
                });*/

        Thread.sleep(1000L); //main函数休眠
    }
}
