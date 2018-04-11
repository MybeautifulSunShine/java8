package cn.test.java;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class StreamFind {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1,  3, 4, 5, 6, 7});
        Optional<Integer> any = stream.filter(i -> i % 2 == 0).findFirst();

        System.out.println(any.get());

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.filter(i->i<100);

        int result = find(new Integer[]{1, 2, 3, 4, 5, 6, 7}, -1, i->i>10);
        System.out.println(result);
    }
    private  static  int find(Integer[] integers, int  defaultvalue, Predicate<Integer> predicate){
        for (Integer integer : integers) {
            if (predicate.test(integer)){
                return integer;
            }
        }
        return defaultvalue;
    }
}
