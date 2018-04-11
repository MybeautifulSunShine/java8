package cn.test.java2;

import cn.test.java.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by SJ217110601 on 2018/3/8.
 */
public class CollectorIntroduce {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));
        //拿出来是绿色的 :
        List<Apple> greenList = list.stream().filter(a -> a.getColor().equals("green")).collect(Collectors.toList());
        //根据颜色进行分组 ,map 颜色做为一个key ,value 是结果
        Optional.ofNullable(greenList).ifPresent(System.out::println);
        Optional.ofNullable(getAppleByOne(list)).ifPresent(System.out::println);
        System.out.println("===================================================");
        Optional.ofNullable(getByOptioal(list)).ifPresent(System.out::println);
        System.out.println("===================================================");
        Optional.ofNullable(getBycollection(list)).ifPresent(System.out::println);
    }
    private static Map<String, List<Apple>> getAppleByOne(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();//先得到一个map对象
        for (Apple a : apples) { //循环得到 apples 里面的每一个对象
            List<Apple> list = map.get(a.getColor()); //通过.get去判断里面有没有对象
            if (list == null) { //如果没有
                list = new ArrayList<>();
                map.put(a.getColor(), list); //把颜色作为key ,
            }
            list.add(a); //添加进去
        }
        return map;
    }

    private  static  Map<String,List<Apple>> getByOptioal(List<Apple> apples){
        Map<String, List<Apple>> map = new HashMap<>();
        apples.stream().forEach((Apple a) -> {
            List<Apple> list1 = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(), list);
                return list;
            });
            list1.add(a);
        });
        //final
        return  map;
    }
    private  static  Map<String,List<Apple>> getBycollection(List<Apple> apples){
        return  apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
