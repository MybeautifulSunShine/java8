package cn.test.java;
import com.sun.xml.internal.ws.api.model.MEP;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static cn.test.java.CollectorAction.menu;

/**
 * Created by SJ217110601 on 2018/1/23.
 */
public class CollectorsAction3 {
    public static void main(String[] args) {
        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollector();
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentiy();
        testSummarizingDouble();
        testSummarizingLong();
        testSummarizingInt();
    }
    //找出不是蔬菜的哪一个
    private static void testPartitioningByWithPredicate() {
        System.out.println("testPartitioningByWithPredicate");
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.of(collect).ifPresent(System.out::println);
    }
    //把肉类和不是分类的拿出来平均的Calories取出来
    private static void testPartitioningByWithPredicateAndCollector() {
        System.out.println("testPartitioningByWithPredicateAndCollector");
        Map<Boolean, Double> map = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(map).ifPresent(System.out::println);
    }
    //Calories最大的哪一种
    private static void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperator");
        Optional<Dish> collect = menu.stream()
                .collect(Collectors
                    .reducing(BinaryOperator.maxBy(
                        Comparator.comparingInt(Dish::getCalories))));
        collect.ifPresent(System.out::println);
    }
    //总和
    private static void testReducingBinaryOperatorAndIdentiy() {
        System.out.println("testReducingBinaryOperatorAndIdentiy");
        Integer integer = menu.stream()
                .map(Dish::getCalories).collect(Collectors.
                reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(integer);
    }
    private static void testReducingBinaryOperatorAndIdentiyAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
        Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }
    private static void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingLong() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

}
