package cn.test.java2;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by SJ217110601 on 2018/2/28.
 */
public class numberStream {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});/*
//        stream.mapToInt(Integer::intValue);
        //拆箱操作
        IntStream anInt = stream.mapToInt(i -> i.intValue());
        //装箱操作
//        Stream<Integer> boxed = anInt.boxed();
        int sum = anInt.filter(i -> i > 3).sum();
        System.out.println(sum);*/

        int a = 9;

        //1..1000 里面有哪一个值可以满足 勾股
        //返回一个数组 int  [a,b,c]
        IntStream.rangeClosed(1, 100)  //相当于切片一样  里面的元素是1-100
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                boxed().
                map(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",c=" + r[1] + ",c=" + r[2]));
        System.out.println(41*41);
        System.out.println(9*9+40*40);

    }
}
