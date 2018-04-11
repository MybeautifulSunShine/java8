package cn.test.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by SJ217110601 on 2018/1/23.
 */
public class CollectorAction {
    public final static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();
        testCounting();
        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }

    //getCalories的平均数
    private static void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    private static void testAveragingInt() {
        System.out.println("testAveragingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "The Average Calories is->" + a)))
                .ifPresent(System.out::println);
        //不能修改,进行另外的封装
       /* List<Dish> list = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        list.add(new Dish("",false,100, Dish.Type.OTHER));
        System.out.println(list);*/
    }

    //对结果进行统计返回long类型的
    private static void testCounting() {
        System.out.println("testCounting");
        Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
//        Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
    }
    //根据分组
    private static void testGroupingByFunction() {
        System.out.println("testGroupingByFunction");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }
    //分组并计算
    private static void testGroupingByFunctionAndCollector() {
        System.out.println("testGroupingByFunctionAndCollector");
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.averagingInt(Dish::getCalories))))
            .ifPresent(System.out::println);
    }
    //返回的map 提供哪一个就封装哪一个
    private static void testGroupingByFunctionAndSupplierAndCollector() {
        System.out.println("testGroupingByFunctionAndSupplierAndCollector");
        TreeMap<Dish.Type, Double> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
    //统计
    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");
        IntSummaryStatistics collect = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
}