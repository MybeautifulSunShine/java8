package cn.test.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class CollectorIntroduce {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));
        List<Apple> apples = Arrays.asList(new Apple());
        List<Apple> green = list.stream().filter(a -> a.getColor().equals("green")).collect(Collectors.toList());
//        Optional.ofNullable(green).ifPresent(System.out::println);
//        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);
//        Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);
        Optional.ofNullable(groupbycollector(list)).ifPresent(System.out::println);
    }

    //根据颜色分组
    private static Map<String,List<Apple>> groupByNormal(List<Apple> apples){
       Map<String,List<Apple>> map= new HashMap<>();
        for (Apple a : apples) {
            List<Apple> list = map.get(a.getColor());
            if (list==null){
               list = new ArrayList<>();
               map.put(a.getColor(),list);
            }
            list.add(a);
        }
        return  map;
    }
    //函数式编程 Lamber表达式
    private  static  Map<String ,List<Apple>> groupByFunction(List<Apple> apples){
        Map<String,List<Apple>> map= new HashMap<>();
        apples.stream().forEach(i->{
            List<Apple> colorlist = Optional.ofNullable(map.get(i.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(i.getColor(), list);
                return list;
            });
            colorlist.add(i);
        });
        return  map;
    }
    //Collector
    private static Map<String,List<Apple>> groupbycollector(List<Apple> apples){
      return   apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
