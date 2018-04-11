package cn.test.java;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by SJ217110601 on 2018/1/19.
 */
public class SimpleStream {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
            Stream<Dish> stream = menu.stream();

        List<String> result = menu.stream().filter(d -> {

            System.out.println("filtering->" + d.getName());
            return d.getCalories() > 300;
        })
                .map(d -> {
                    System.out.println("map->" + d.getName());
                    return d.getName();
                })
                .limit(3).collect(toList());


        System.out.println("=======================");
        System.out.println(result);
//        stream.forEach(System.out::print);
//        stream.forEach(System.out::print);

//        List<String> list = getMenu(menu);
//        System.out.println(list);
//        List<String> stream = getMenuByStream(menu);
//        System.out.println(stream);
    }
    private  static   List<String> getMenuByStream(List<Dish> menu){
        return  menu.parallelStream().filter((d)-> d.getCalories()<400
        ).sorted(Comparator.comparing((d)->d.getCalories())).map(dish -> dish.getName()).collect(toList());
    }
    private static List<String> getMenu(List<Dish> menu) {
        ArrayList<Dish> list = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400)
                list.add(dish);
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //排序
            Collections.sort(list, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

            List<String> list1 = new ArrayList<>();
            for (Dish dish1 : list) {
                list1.add(dish1.getName());
            }
            return list1;
        }

    }