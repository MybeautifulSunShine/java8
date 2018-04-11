package cn.test.java2;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by SJ217110601 on 2018/2/28.
 */
public class StreamMatch {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> any = stream.filter(i -> i >10).findAny();
        System.out.println(any.orElse(-1));

        Stream<Integer> stream1 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> integer = stream1.filter(i -> i %2== 0).findFirst();

        integer.ifPresent(System.out::print);

        System.out.println(integer.filter(i->i==2).get());


        Integer option = findOption(new Integer[]{1, 2, 3, 4, 5, 6, 7}, -1, i -> i < 10);
        System.out.println(option);
    }
    public static Integer findOption(Integer[] values, int Depaultvalue, Predicate<Integer> predicate){
        for (int value : values) {
            if (predicate.test(value));
                 return value;
        }
       return Depaultvalue;
    }

}
