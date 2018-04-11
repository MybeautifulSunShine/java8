package cn.test.java;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class StreamReduce {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Integer reduce = stream.reduce(0, (i, j) -> i + j);
//        System.out.println(reduce);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
//        stream.reduce((i,j)->j+i).ifPresent(System.out::print);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce((i,j)->{
            return  i>j?i:j;
        }).ifPresent(System.out::println);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::max).ifPresent(System.out::println);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::min).ifPresent(System.out::println);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce((i,j)->i>j?i:j).ifPresent(System.out::println);
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        //穿件成一个
        Integer integer = stream.filter(i -> i % 2 == 0).reduce(1, (i, j) -> i * j);
        Optional.of(integer).ifPresent(System.out::println);

    }
}
