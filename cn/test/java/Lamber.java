package cn.test.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by SJ217110601 on 2018/1/18.
 */
public class Lamber {
    public static void main(String[] args) {
        Comparator<Apple> bycplor=new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        ArrayList<Apple> list = new ArrayList<>();
        list.sort(bycplor);
        Comparator<Apple> byColor2=(o1,o2)->o1.getColor().compareTo(o2.getColor());
                                                 //参数     表达式
        Function<String, Object> fun = s -> s.length();
        Predicate<Apple> filter =  a -> (a.getColor().equals("green"));
        Runnable runnable=()->{};
        Supplier<Apple> a=Apple::new;

    }
}
