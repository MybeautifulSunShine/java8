package cn.test.java8;

import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/7 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class FutureInAction2 {

    public static void main(String[] args)
            throws ExecutionException, InterruptedException, TimeoutException {
        //创建一个线程  一定到shat down不然会阻塞 跟其它的线程不一样
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000L);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "I am Error.";
            }
        });

        //...
        //...
        //...
        //...
        //最多等10秒如果没有拿到值的话  相当于这次获取东西失败了
        //String value = future.get(10, TimeUnit.MICROSECONDS);
        while (!future.isDone()) {//如果没有结束
            Thread.sleep(10);
        }
        System.out.println(future.get());
        executorService.shutdown();
    }
}