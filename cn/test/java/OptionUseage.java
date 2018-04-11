package cn.test.java;

import java.util.Optional;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class OptionUseage {
    public static void main(String[] args) {
        Optional<Insurance> empty = Optional.<Insurance>empty();
//        empty.get();
        Optional<Insurance> optional = Optional.of(new Insurance());
//        optional.get();
//        Optional<Insurance> optional1 = Optional.ofNullable(null);
//        optional1.orElseGet(Insurance::new);
//        optional1.orElse(new Insurance());
//        optional1.orElseThrow(()->new RuntimeException("NOT FIND"));
//        Insurance insurance = optional.filter(i -> i.getName() != null).get();
//        System.out.println(insurance);
//        Optional<String> s = optional.map(i -> i.getName());
//            System.out.println(s.orElse("ampey Value"));
//            System.out.println(s.isPresent());
//            s.ifPresent(System.out::println);
        System.out.println(getName(null));
        System.out.println(getNameByOpyional(null));
    }
    private static String getName(Insurance insurance){
        if (null==insurance)
            return "unkonw";
        return insurance.getName();
    }
    private static String getNameByOpyional(Insurance insurance){
        return  Optional.ofNullable(insurance).map(Insurance::getName).orElse("numkonw");
    }
}
