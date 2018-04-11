package cn.test.java;

import com.sun.deploy.util.SyncAccess;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by SJ217110601 on 2018/1/22.
 */
public class StreamInAction {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //算出2011年的,并根据value进行排序
        List<Transaction> list = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(t -> t.getValue()))
                .collect(toList());
        System.out.println(list);
        //城市的名称
        transactions.stream().map(i->i.getTrader().getCity())
                .distinct().forEach(System.out::println);
        System.out.println("--------------------------------------------");

        //从Cambridge 找到所有的交易者并按他们的名字排序
        transactions.stream().map(Transaction::getTrader)
                .filter(t->t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(t->t.getName()))
                .forEach(System.out::println);
        //按字母顺序返回一串所有交易者的姓名
        String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (name1, name2) -> name1 + name2);
        System.out.println(reduce);
        //找出是米兰的交易员吗 Milan
        boolean b = transactions.stream().map(Transaction::getTrader).anyMatch(t -> t.getCity().equals("Milan"));
        boolean b1 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(b1);
        //transaction的value打印出来是生活在剑桥的的交易员
//        //6.Print all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        //7.What’s the highest value of all the transactions?最大的数值?
        Optional<Integer> MaxVAlue = transactions.stream().map(Transaction::getValue).reduce((i, j) -> i > j ? i : j);
        System.out.println(MaxVAlue.get());
        //找到最小的
        Optional<Integer> reduce1 = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
    }
}
