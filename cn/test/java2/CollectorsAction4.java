package cn.test.java2;

import cn.test.java.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static cn.test.java2.CollectorsAction.menu;

/**
 * Created by SJ217110601 on 2018/3/8.
 */
public class CollectorsAction4 {
    public static void main(String[] args) {
        testSummingDouble();
        testSummingLong();
        testSummingInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        toMap();
        /*testToList();
        testToSet();*/


        testToMap();
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorAndSupplier();
        testToSet();
    }

    private static void testSummingDouble() {
        System.out.println("testSummingDouble");
        Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
                .ifPresent(System.out::println);
    }

    private static void testSummingLong() {
        System.out.println("testSummingLong");
        Optional.of(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testSummingInt() {
        System.out.println("testSummingInt");
        Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testToCollection() {
        System.out.println("testToCollection");
        Optional.of(menu.stream().filter(d -> d.getCalories() > 600).collect(Collectors.toCollection(LinkedList::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    private static void testToConcurrentMap() {
        System.out.println("testToConcurrentMap");
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    /**
     * Type:Total
     */
    private static void testToConcurrentMapWithBinaryOperator() {
        System.out.println("testToConcurrentMapWithBinaryOperator");
        Optional.of(menu.stream()
                //不懂
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    /**
     * Type:Total
     */
    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();

        System.out.println("testToConcurrentMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    private static void toMap() {
        System.out.println("toMap");
        Optional.of(menu.stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    /**
     * Type:Total
     */
    private static void testToMapWithBinaryOperator() {
        System.out.println("testToMapWithBinaryOperator");
        Optional.of(menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    /**
     * Type:Total
     */
    private static void testToMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b, Hashtable::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    //线程安全的一个 map 效率没有ConcurrentMap高但是map是安全的
    private static void testToMap() {
        System.out.println("testToMap");
        Optional.of(menu.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toMap(Dish::getName, Dish::getCalories),
                        Collections::synchronizedMap))
        )
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    private static void testToSet() {
        System.out.println("-------------------------");
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet()))
                .ifPresent(r -> {
                    System.out.println(r.getClass());
                    System.out.println(r);
                });
    }

}
