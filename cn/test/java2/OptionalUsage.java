package cn.test.java2;

import java.util.Optional;

/**
 * Created by SJ217110601 on 2018/3/8.
 */
public class OptionalUsage {
    public static void main(String[] args) {
        Optional<Insurance> insuranceOptional = Optional.<Insurance>empty();

//        insuranceOptional.get();

        Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());

        /*insuranceOptional1.get();

        Optional<Insurance> objectOptional = Optional.ofNullable(null);

        objectOptional.orElseGet(Insurance::new);

        objectOptional.orElse(new Insurance());

        objectOptional.orElseThrow(RuntimeException::new);

        objectOptional.orElseThrow(() -> new RuntimeException("Not have reference"));
*/
/*
        Insurance insurance = insuranceOptional1.filter(i -> i.getName() != null).get();
        System.out.println(insurance);*/

/*        Optional<String> nameOptional = insuranceOptional1.map(i -> i.getName());

        System.out.println(nameOptional.orElse("empty Value"));

        System.out.println(nameOptional.isPresent());

        nameOptional.ifPresent(System.out::println);*/

        System.out.println(getByInurance(null));
        System.out.println(getByOptioal(null));

    }
    private  static String getByInurance(Insurance insurance){
        if (insurance==null)
            return "nukonw";
        return insurance.getName();
    }
    private static String getByOptioal(Insurance insurance){
       return Optional.ofNullable(insurance).map(Insurance::getName).orElse("nukonw");
    }
}
