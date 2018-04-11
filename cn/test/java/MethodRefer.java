package cn.test.java;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by SJ217110601 on 2018/1/19.
 */
public class MethodRefer {
    public static void main(String[] args) {
        Consumer<String > consumer=(s)-> System.out.println(s);
        userConsumer(consumer,"HAHAHHAHAh");
        List<Apple> apples = Arrays.asList(new Apple("green", 100), new Apple("Gnnn", 50), new Apple("huoyu", 500));
        System.out.println(apples);
        apples.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));

        System.out.println(apples);
        System.out.println("_____________________");
        apples.stream().forEach(a-> System.out.println(a));
//        apples.stream().forEach(System.out::print);
        int i = Integer.parseInt("1232");

        Function<String, Integer> fun = Integer::parseInt;
        Integer apply = fun.apply("456");
        System.out.println();
        String hello = new String("hello");
        Function<Integer, Character> f3 = hello::charAt;
        Character apply1 = f3.apply(4);
        System.out.println(apply1);
        System.out.println("----------------------");
        Supplier<String> runnable = String::new;
        String s = runnable.get();
        System.out.println(s);

        BiFunction<String,Long,Apple> bifun = Apple::new;
        Apple red = bifun.apply("red", 7880L);
        System.out.println(red);

        ThreeFunction<String,Long,String,ComplexApple> co=ComplexApple::new;
        ComplexApple appley = co.appley("呵呵", 112L, "wanwna");
        System.out.println(appley);
        List<Apple> apple2 = Arrays.asList(new Apple("green", 100), new Apple("Gnnn", 50), new Apple("huoyu", 500));


    }
    private  static <T>void userConsumer(Consumer<T> cu,T t){
        cu.accept(t);
        cu.accept(t);
    }
    

}
