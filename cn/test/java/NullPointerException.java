package cn.test.java;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class NullPointerException {
    public static void main(String[] args) {
        //String s = getinsuName(new Person());
//        String s = getInNameByDeeo(new Person());

    }
    /*//空指针解决方法一
    private  static  String getInNameByDeeo(Person person){
        if (null !=person){
            Car car = person.getCar();
            if (null!=car){
                Insurance insurance = car.getInsurance();
                if (null !=insurance){
                    return insurance.getName();
                }
            }
        }
        return "BUNNOW";
    }
    //空指针解决方法二
    private static String getInsuNameExit(Person person){
        final String defaultValue="UNKNOWN";
        if (null==person)
            return defaultValue;
        Car car = person.getCar();
        if (null==car)
            return defaultValue;
        Insurance insurance = car.getInsurance();
        if (null==insurance)
            return defaultValue;

        return insurance.getName();
    }
    private static String  getinsuName(Person person){
        return person.getCar().getInsurance().getName();
    }*/
}
