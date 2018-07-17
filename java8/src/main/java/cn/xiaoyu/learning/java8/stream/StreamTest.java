package cn.xiaoyu.learning.java8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Stream 流 - 处理数据的一种方式
 * +--------------------+    +------+   +------+   +---+   +-------+
 * | stream of elements +--> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+    +------+   +------+   +---+   +-------+
 */
public class StreamTest {
    public static void main(String[] args) {
        // 生成流
        List<String> strings = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        List<String> collect = strings.stream().filter(string -> !string.isEmpty()).map(n -> n + ",").collect(Collectors.toList());
        collect.forEach(System.out::print);
        System.out.println();

        // forEach - 迭代流中的数据
        Random random = new Random();
        random.ints(0, 10).limit(5).forEach(System.out::print);
        System.out.println();

        // map - 映射每个元素对应的结果
        List<Integer> intList = Arrays.asList(3, 2, 1, 3, 4, 2);
        List<Integer> intList2 = intList.stream().map(i -> i * 2).distinct().collect(Collectors.toList());
        intList2.forEach(System.out::print);
        System.out.println();

        // filter - 设置过滤条件
        strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        // 并行（parallel）程序
        strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        // limit - 获取指定数量的流
        // sorted - 对流进行排序
        random.ints(0, 10).limit(5).sorted().forEach(System.out::print);
        System.out.println();


        // 统计
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
