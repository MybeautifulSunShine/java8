package cn.test.java2;

import cn.test.java.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;

/**
 * Created by SJ217110601 on 2018/3/8.
 */
public class CollectorsAction {
    public static List<Dish> menu = Arrays.asList(
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
        testcollectingAndThen();
        testcounting();
        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }

    private static void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingInt() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingLong() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testcollectingAndThen() {
        System.out.println("testcollectingAndThen");
        Optional.ofNullable(menu.stream().collect(Collectors.
                collectingAndThen(averagingInt(Dish::getCalories),
                        a -> "CAlumniate" + a)))
                .ifPresent(System.out::println);

//        List<Dish> collect = menu.stream().filter(d -> d.getType().equals(Dish.Type.OTHER)).collect(toList());

      /*  List<Dish> collect1 = menu.stream().filter
                (d -> d.getType().equals(Dish.Type.OTHER)).
                collect(collectingAndThen(toList(),
                        Collections::unmodifiableList));
        collect1.add(new Dish("", false, 100, Dish.Type.OTHER));*/
//        System.out.println(collect1);
    }

    //计算有几个
    private static void testcounting() {
        System.out.println("counting");
        Optional.of(menu.stream()
                .collect(Collectors.counting()))
                .ifPresent(System.out::println);
    }

    //分组
    private static void testGroupingByFunction() {
        System.out.println("testGroupingByFunction");
        Optional.ofNullable(menu.stream().
                collect(Collectors.groupingBy(Dish::getType))).
                ifPresent(System.out::println);
    }

    //分组后计算 ,分组后平均值
    private static void testGroupingByFunctionAndCollector() {
        System.out.println("testGroupingByFunctionAndCollector");
        Optional.ofNullable(menu.stream().collect(Collectors.
                groupingBy(Dish::getType,
                        Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    //
    private static void testGroupingByFunctionAndSupplierAndCollector() {
        //本来返回有个map
        Map<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        //先在要返回一个treemap
        Map<Dish.Type, Double> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(map.getClass()).ifPresent(System.out::println);
        Optional.of(collect.getClass()).ifPresent(System.out::println);
    }

    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");
        IntSummaryStatistics collect = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
}
