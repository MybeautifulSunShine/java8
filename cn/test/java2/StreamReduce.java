package cn.test.java2;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by SJ217110601 on 2018/2/28.
 */
public class StreamReduce {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        //打出全部的和   初始值是0
        Integer reduce = stream.reduce(0, (i, j) -> i + j);
        System.out.println(reduce+"-------------------");

        Stream<Integer> stream3 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        //里面的方法
        stream3.reduce(Integer::sum).ifPresent(System.out::print);

        Stream<Integer> stream2 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        stream2.reduce((i, j) -> i + j).ifPresent(System.out::print);
    //根据里面的function进行操作
        Stream<Integer> stream1 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream1.reduce((i, j) -> i > j ? i : j).ifPresent(System.out::print);


        Stream<Integer> stream4 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream4.reduce(Integer::max).ifPresent(System.out::print);

        Stream<Integer> stream5 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream5.reduce(Integer::min).ifPresent(System.out::println);
        //里面偶数的乘积
        Stream<Integer> stream6 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        int reduce1 = stream6.filter(i -> i % 2 == 0).reduce(1, (i, j) -> i * j);
        System.out.println(reduce1);

    }
}
