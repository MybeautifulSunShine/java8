package cn.test.java;

import com.sun.javaws.security.AppPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Created by SJ217110601 on 2018/1/18.
 */
public class lambdsUsage {
    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    private static List<Apple> fileByWright(List<Apple>soure, LongPredicate longPredicate){
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : soure) {
            if (longPredicate.test(apple.getWeight())) {
                list.add(apple);
            }
        }
        return list;
    }
    private static List<Apple> fileByBigWright(List<Apple>soure, BiPredicate<String ,Long> predicate){
        ArrayList<Apple> list = new ArrayList<>();
        for (Apple apple : soure) {
            if (predicate.test(apple.getColor(),apple.getWeight())) {
                list.add(apple);
            }
        }
        return list;
    }
    private  static void TestConsumer(List<Apple> list, Consumer<Apple>consumer){
        for (Apple apple : list) {
            consumer.accept(apple);
        }
    }
    private static void simpleBiConsumer(String c, List<Apple> source, BiConsumer<Apple, String> consumer) {
        for (Apple a : source) {
            consumer.accept(a, c);
        }
    }
    private  static  String testfunction(Apple apple,Function<Apple,String> function){
       return function.apply(apple);
    }
    private  static  Apple testBiffunction(String color,Long weight,BiFunction<String,Long,Apple> fun){
        return  fun.apply(color,weight);
    }
    public static void main(String[] args) {
       /* Runnable r2=()-> System.out.println("hello");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("HELLO");
            }
        };
        process(r2);
        process(runnable);
        process(()-> System.out.println("Hello"));
    }
    private static void process(Runnable r){
        r.run();
      }*/
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("red", 100));
        List<Apple> red = filter(list, (apple) -> apple.getColor().equals("red"));
//        System.out.println(red);
        List<Apple> list1 = fileByWright(list, w -> w > 100);
        List<Apple> red1 = fileByBigWright(list, (c, w) -> c.equals("red") && w >= 100);
        System.out.println(list1);
        System.out.println(red1);
        System.out.println("_______________________________________");
        TestConsumer(list,a-> System.out.println(a));
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        simpleBiConsumer("XXX",list,(a,s)-> System.out.println(s+a.getColor()+"weighe"+a.getWeight()));
        System.out.println("--------------------------------");
        String yellow = testfunction(new Apple("yellow", 200), (apple) -> apple.toString());
        System.out.println(yellow);
        System.out.println("*******************************");
        IntFunction<Double>f=i->i*100*0.5;
        Double apply = f.apply(10);
        System.out.println(apply);
        Apple bight = testBiffunction("bight",  230L, (s, w) -> new Apple(s, w));
        System.out.println(bight);
        Supplier<String> string = String::new;
        System.out.println(string.get().getClass());
        Apple green = create(() -> new Apple("Green", 500));
        System.out.println(green);

    }
    private  static  Apple create(Supplier<Apple> apple){
        return  apple.get();
    }
}
