package cn.test.java2;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Created by SJ217110601 on 2018/3/30.
 */
public class Stream {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(0, 10);
        Spliterator.OfInt anInt = intStream.spliterator();
        Consumer<Integer> consumer=i->System.out.println(i);
        anInt.forEachRemaining(consumer);
    }
}
