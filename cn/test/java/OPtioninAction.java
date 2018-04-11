package cn.test.java;

import java.util.Optional;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class OPtioninAction {
    public static void main(String[] args) {
        System.out.println(getNameByOption(null));;
        Optional.ofNullable(getNameByOption(null)).ifPresent(System.out::println);
    }
    private static String getNameByOption(Person person){
        return Optional.ofNullable(person)
            .flatMap(Person::getCar)
               .flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("Unkonw");
    }

}
