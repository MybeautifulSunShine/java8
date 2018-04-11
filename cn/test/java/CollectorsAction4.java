package cn.test.java;

import jdk.internal.org.objectweb.asm.Handle;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static cn.test.java.CollectorAction.menu;
/**
 * Created by SJ217110601 on 2018/1/23.
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
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorAndSupplier();
        testToSet();
        testToMap();

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
        Optional.of(menu.stream().
                filter(d->d.getCalories()>600).
                collect(Collectors.toCollection(LinkedList::new)))
                .ifPresent(System.out::println);
    }
    private static void testToConcurrentMap() {
        System.out.println("testToConcurrentMap");
        Optional.of(menu.stream().collect(Collectors.
                toConcurrentMap(Dish::getName,dish -> dish.getCalories())))
                .ifPresent((v)->{System.out.println(v);
                    System.out.println(v.getClass());
        });
    }
    /**
     * Type :total
     */
    private static void testToConcurrentMapWithBinaryOperator() {
        System.out.println("testToConcurrentMapWithBinaryOperator");
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType,c->1l, (a, b) -> a + b)))
                .ifPresent((v) -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }
    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        Optional.of(menu.stream().collect(Collectors.
                toConcurrentMap(Dish::getType,v->1L,
                        (a,b)->a+b, ConcurrentSkipListMap::new)))
                .ifPresent((v)->{
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

    }
    private static void testToList() {
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()))
        .ifPresent(v->{
            System.out.println(v);
            System.out.println(v.getClass());
        });
    }

    private static void testToSet() {
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet()))
                .ifPresent(v->{
                    System.out.println(v.getClass());
                    System.out.println(v);
                });
    }

    private static void testToMap(){
        //线程不安全
      /*  Optional.of(menu.stream().collect(Collectors.toMap(Dish::getName,Dish::getCalories)))
                .ifPresent(v->{
                    System.out.println(v);
                    System.out.println(v.getClass());
                });*/

      //线程安全的
        Optional.of(menu.stream().collect(Collectors.
                collectingAndThen(Collectors.
                        toMap(Dish::getName,Dish::getCalories),Collections::synchronizedMap)))
                .ifPresent(v->{
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }
    private static void testToMapWithBinaryOperator() {
        System.out.println("testToMapWithBinaryOperator");
        Optional.of(menu.stream().collect(Collectors.toMap(Dish::getType,v->1l,(b1,b2)->b1+b2)))
                .ifPresent(i->{
                    System.out.println(i);
                    System.out.println(i.getClass());
                });
    }
    private static void testToMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream().collect(Collectors.toMap(Dish::getType,v->1l,(a,v)->a+v, Hashtable::new)))
                .ifPresent(v->{
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

}
