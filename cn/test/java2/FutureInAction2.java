package cn.test.java2;

import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/7 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class FutureInAction2 {

    public static void main(String[] args)
            throws ExecutionException, InterruptedException, TimeoutException {
        //理解为一个执行服务 单线程,始终保持一个线程去执行,
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            try {
                //等待100秒
                Thread.sleep(10000L);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "I am Error.";
            }
        });

        //...
        //...可以做自己的逻辑
        //...
        //...
        //最多等10 秒 get的时候就会阻塞住
        //String value = future.get(10, TimeUnit.MICROSECONDS);
        //如果没有结束
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        //执行完之后就拿出来
        System.out.println(future.get());
        //必须
        executorService.shutdown();
    }
}