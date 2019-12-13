package com.wxf.jdkdemo.java8.lambada;

import com.wxf.jdkdemo.java8.lambada.been.Trader;
import com.wxf.jdkdemo.java8.lambada.been.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @ClassName TestStreamaAPI1
 * @Description
 * @Author wangxuefei
 * @Date 2019/12/12 17:16
 * @Version V1.0
 **/
public class TestStreamAPI1 {
    List<Transaction> transactions = null;

    @Before
    public void before() throws Exception {
        Trader raoul = new Trader("raoul", "cambridge");
        Trader mario = new Trader("mario", "milan");
        Trader alan = new Trader("alan", "cambridge");
        Trader brian = new Trader("brian", "cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
     *功能描述 跳出2011年发生的所有交易，并按交易额排序（从低到高）
     * @author wangxuefei
     * @date 2019/12/12
     * @param
     * @return void
     */
    @Test
    public void test1() {
        transactions.stream()
                .filter(t->t.getYear()==2011)
                .sorted((t1,t2)->Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }
/**
 *功能描述 2、交易员都在哪些不同的城市工作过
 * @author wangxuefei
 * @date 2019/12/13
 * @param
 * @return void
 */
    @Test
    public void test2() {
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }


    /**
     *功能描述 查找所有赖子剑桥的交易员，并按照姓名排序
     * @author wangxuefei
     * @date 2019/12/13
     * @param
     * @return void
     */
    @Test
    public void test3() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("cambridge"))
                .sorted((t1,t2)->t1.getTrader().getName().compareTo(t2.getTrader().getName()))
                .distinct()
                .forEach(System.out::println);
    }

    /**
     *功能描述 返回所有交易员的名字字符串，按字母顺序排序
     * @author wangxuefei
     * @date 2019/12/13
     * @param
     * @return void
     */
    @Test
    public void test4(){
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .forEach(System.out::println);

        System.out.println("-----------------------");

        String str = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .reduce("", String::concat);

        System.out.println(str);

        System.out.println("------------------------");

        //调用自定义方法
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .flatMap(TestStreamAPI1::filterCharacter)
                .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                .forEach(System.out::println);
    }

    public static Stream<String> filterCharacter(String str) {
        List<String> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }

    /**
     *功能描述 5、有没有交易员是在米兰工作的
     * @author wangxuefei
     * @date 2019/12/13
     * @param
     * @return void
     */
    @Test
    public void test5(){
        boolean b1 = transactions.stream()
                .allMatch(transaction -> transaction.getTrader().getCity().equals("milan"));
        System.out.println(b1);
    }

    /**
     *功能描述 打印生活在剑桥的交易员的所有交易额
     * @author wangxuefei
     * @date 2019/12/13
     * @param
     * @return void
     */
    @Test
    public void test6(){
        Optional<Integer> sum = transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(sum.get());
    }

    /**
     *功能描述 所有交易中，最高的交易额是多少
     * @author wangxuefei
     * @date 2019/12/13
     * @param
     * @return void
     */
    @Test
    public void test7(){
        Optional<Integer> max = transactions.stream()
                .map(transaction -> transaction.getValue())
                .max(Integer::compareTo);
        System.out.println(max.get());
    }

    /**
     *功能描述 找到交易额最小的交易
     * @author wangxuefei
     * @date 2019/12/13
     * @param
     * @return void
     */
    @Test
    public void test8(){
        Optional<Transaction> op = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(op.get());
    }
}
