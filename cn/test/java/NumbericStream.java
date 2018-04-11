package cn.test.java;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class NumbericStream {
    public static void main(String[] args) {
       /* Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});

        Stream<Integer> integerStream = stream.filter(i -> i.intValue() > 3);

        Integer reduce = integerStream.reduce(0, Integer::sum);
       //int的类型操作减少内容
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        IntStream stream1 = stream.mapToInt(i -> i.intValue());
        int sum = stream1.filter(i -> i > 3).sum();
        System.out.println(sum);*/
//        System.out.println(reduce);
//        stream1.filter(i->i>3).reduce(0,(i,j)->i+j);
//        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});'
        int a=9;
        //相当于(1..100)
        IntStream stream = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0);
        stream.forEach(System.out::println);

        //算勾股定律

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));

        System.out.println("=======================");


        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));
    }
}
