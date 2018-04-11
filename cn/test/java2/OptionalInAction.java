package cn.test.java2;

import cn.test.java.Car;
import cn.test.java.Insurance;

import java.util.Optional;

/**
 * Created by SJ217110601 on 2018/3/8.
 */
public class OptionalInAction {
    public static void main(String[] args) {
        //改造实体类
       Optional.ofNullable(getInsertByname(null)).ifPresent(System.out::println) ;
    }
    private static String getInsertByname(Person person){
       /* Optional<Optional<cn.test.java.Car>> car = Optional.ofNullable(person).map(Person::getCar);*/
       return Optional.ofNullable(person).
                flatMap(Person::getCar).flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("unknow");
    }
}
