package cn.test.java2;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by SJ217110601 on 2018/2/28.
 */
public class CreateStream {
    public static void main(String[] args) {
        //创建一个STream
        Stream<Obj> stream = createObjStreamFromGenerate();
        stream.forEach(System.out::print);

    }

    private static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    static class ObjSupplier implements Supplier {

        private int index = 0;

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Object get() {
            index = random.nextInt(100);
            return new Obj(index, "Name->" + index);
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
